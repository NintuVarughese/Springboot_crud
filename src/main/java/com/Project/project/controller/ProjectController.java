package com.Project.project.controller;

import com.Project.project.exception.ProjectNotFoundException;
import com.Project.project.model.Project;
import com.Project.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping("/project")
    public Project newProject(@RequestBody Project newProject) {
        return projectRepository.save(newProject);
    }

    @GetMapping("/project")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/project/{id}")
    Project getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @PutMapping("/project/{id}")
    Project updateProject(@RequestBody Project newProject, @PathVariable Long id) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setName(newProject.getName());
                    project.setRisk(newProject.getRisk());
                    project.setMilestone(newProject.getMilestone());
                    project.setBudget(newProject.getBudget());
                    project.setDependency(newProject.getDependency());
                    project.setStartDate(newProject.getStartDate()); // Update startDate
                    project.setEndDate(newProject.getEndDate());     // Update endDate
                    return projectRepository.save(project);
                }).orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @DeleteMapping("/project/{id}")
    public String deleteProject(@PathVariable Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException(id);
        }
        projectRepository.deleteById(id);
        return "Project With id " + id + " has been deleted success.";
    }

    @GetMapping("/project/sorted/{sortBy}")
    public List<Project> getSortedProjects(@PathVariable String sortBy) {
        switch (sortBy.toLowerCase()) {
            case "budget":
                return projectRepository.findAllByOrderByBudgetAsc();
            case "milestone":
                return projectRepository.findAllByOrderByMilestoneAsc();
            case "risk":
                return projectRepository.findAllByOrderByRiskCustom(); // Use custom sorting for risk
            case "dependency":
                return projectRepository.findAllByOrderByDependencyAsc();
            case "date":
                return projectRepository.findAllByOrderByStartDateAsc();
            default:
                throw new IllegalArgumentException("Invalid sort criteria: " + sortBy);
        }
    }
}