package com.studentproject.backend.controller;

import com.studentproject.backend.domain.Project;
import com.studentproject.backend.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<Project> getProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable String id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable String id, @RequestBody Project projectDetails) {
        return projectService.getProjectById(id).map(project -> {
            if (projectDetails.getTitle() != null) project.setTitle(projectDetails.getTitle());
            if (projectDetails.getDescription() != null) project.setDescription(projectDetails.getDescription());
            if (projectDetails.getDeadline() != null) project.setDeadline(projectDetails.getDeadline());
            if (projectDetails.getStatus() != null) project.setStatus(projectDetails.getStatus());
            return ResponseEntity.ok(projectService.saveProject(project));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable String id) {
        return projectService.getProjectById(id).map(project -> {
            projectService.deleteProject(project);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
