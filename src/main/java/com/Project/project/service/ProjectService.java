package com.Project.project.service;

import com.Project.project.model.Project;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project newProject);

    List<Project> getAllProjects();

    Project getProjectById(Long id);

    Project updateProject(Project newProject, Long id);

    String deleteProject(Long id);
}
