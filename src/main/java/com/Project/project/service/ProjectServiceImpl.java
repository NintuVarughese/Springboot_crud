package com.Project.project.service;

import com.Project.project.exception.ProjectNotFoundException;
import com.Project.project.model.Project;
import com.Project.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project saveProject(Project newProject) {
        return projectRepository.save(newProject);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @Override
    public Project updateProject(Project newProject, Long id) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setName(newProject.getName());
                    project.setRisk(newProject.getRisk());
                    project.setMilestone(newProject.getMilestone());
                    project.setBudget(newProject.getBudget());
                    project.setDependency(newProject.getDependency());
                    project.setStartDate(newProject.getStartDate()); // Set startDate
                    project.setEndDate(newProject.getEndDate());     // Set endDate
                    return projectRepository.save(project);
                }).orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @Override
    public String deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException(id);
        }
        projectRepository.deleteById(id);
        return "Project With id " + id + " has been deleted successfully.";
    }
}
