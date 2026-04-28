package com.studentproject.backend.controller;

import com.studentproject.backend.domain.Submission;
import com.studentproject.backend.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<Submission> createSubmission(@RequestBody Submission submission) {
        return ResponseEntity.ok(submissionService.createSubmission(submission));
    }

    @GetMapping("/group/{groupId}")
    public List<Submission> getSubmissionsByGroup(@PathVariable String groupId) {
        return submissionService.getSubmissionsByGroup(groupId);
    }
    
    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Submission> updateSubmission(@PathVariable String id, @RequestBody Submission submissionDetails) {
        return submissionService.getSubmissionById(id).map(submission -> {
            if (submissionDetails.getStatus() != null) submission.setStatus(submissionDetails.getStatus());
            if (submissionDetails.getGrade() != null) submission.setGrade(submissionDetails.getGrade());
            return ResponseEntity.ok(submissionService.saveSubmission(submission));
        }).orElse(ResponseEntity.notFound().build());
    }
}
