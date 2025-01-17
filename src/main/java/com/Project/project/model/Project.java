package com.Project.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
public class Project
{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String risk;
    private LocalDate timeline;
    private String milestone;
    private BigDecimal budget;
    private Long dependency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public LocalDate getTimeline() {
        return timeline;
    }

    public void setTimeline(LocalDate timeline) {
        this.timeline = timeline;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Long getDependency() {
        return dependency;
    }

    public void setDependency(Long dependency) {
        this.dependency = dependency;
    }


}
