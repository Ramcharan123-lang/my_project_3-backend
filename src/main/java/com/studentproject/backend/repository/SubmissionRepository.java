package com.studentproject.backend.repository;

import com.studentproject.backend.domain.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, String> {
    List<Submission> findByProjectId(String projectId);
    List<Submission> findByGroupId(String groupId);
}
