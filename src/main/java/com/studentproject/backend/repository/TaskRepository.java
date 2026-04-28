package com.studentproject.backend.repository;

import com.studentproject.backend.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByProjectId(String projectId);
    List<Task> findByAssignedTo(String assignedTo);
}
