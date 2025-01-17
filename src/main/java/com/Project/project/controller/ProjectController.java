package com.Project.project.controller;

import com.Project.project.exception.ProjectNotFoundException;
import com.Project.project.model.Project;
import com.Project.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3002")
public class ProjectController {


    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping("/project")
    public Project newProject(@RequestBody Project newProject) {
        return projectRepository.save(newProject);
    }

    @GetMapping("/project")
    public List<Project> getAllProjects() {
        return projectRepository.findAll(); // Call findAll on the injected instance, not the class
    }

    @GetMapping("/project/{id}")
    Project getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @PutMapping("/project/{id}")
    Project UpdateProject(@RequestBody Project newProject, @PathVariable Long id) {
        return projectRepository.findById(id)
                .map(Project -> {
                    Project.setName(newProject.getName());
                    Project.setRisk(newProject.getRisk());
                    Project.setTimeline(newProject.getTimeline());
                    Project.setMilestone(newProject.getMilestone());
                    Project.setBudget(newProject.getBudget());
                    Project.setDependency(newProject.getDependency());
                    return projectRepository.save(Project);
                }).orElseThrow(() -> new ProjectNotFoundException(id));

    }

    @DeleteMapping("/project/{id}")
    public String deleteProject(@PathVariable Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException(id);

        }
        projectRepository.deleteById(id);
        return "Project With id" + id + " has been deleted success.";
    }
}
