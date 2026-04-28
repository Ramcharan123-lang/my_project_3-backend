package com.studentproject.backend.service;

import com.studentproject.backend.domain.Project;
import com.studentproject.backend.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Optional<Project> getProjectById(String id) {
        return projectRepository.findById(id);
    }

    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }
}
