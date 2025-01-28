package com.Project.project.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long id){
        super("could not found the user with id " + id);
    }
}