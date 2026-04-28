package com.studentproject.backend.repository;

import com.studentproject.backend.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, String> {
    List<Project> findByAssignedGroup(String assignedGroup);
}
