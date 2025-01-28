package com.Project.project.repository;

import com.Project.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Sort by budget in ascending order
    List<Project> findAllByOrderByBudgetAsc();

    // Sort by milestone in ascending order
    List<Project> findAllByOrderByMilestoneAsc();

    // Custom sorting for risk: High → Medium → Low
    @Query("SELECT p FROM Project p ORDER BY " +
            "CASE p.risk " +
            "WHEN 'High' THEN 1 " +
            "WHEN 'Medium' THEN 2 " +
            "WHEN 'Low' THEN 3 " +
            "ELSE 4 END")
    List<Project> findAllByOrderByRiskCustom();

    // Sort by dependency in ascending order
    List<Project> findAllByOrderByDependencyAsc();

    // Sort by start date in ascending order
    List<Project> findAllByOrderByStartDateAsc();
}