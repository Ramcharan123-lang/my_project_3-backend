package com.studentproject.backend.service;

import com.studentproject.backend.domain.Submission;
import com.studentproject.backend.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    private final SubmissionRepository submissionRepository;

    public Submission createSubmission(Submission submission) {
        return submissionRepository.save(submission);
    }

    public List<Submission> getSubmissionsByGroup(String groupId) {
        return submissionRepository.findByGroupId(groupId);
    }
    
    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Optional<Submission> getSubmissionById(String id) {
        return submissionRepository.findById(id);
    }

    public Submission saveSubmission(Submission submission) {
        return submissionRepository.save(submission);
    }
}
