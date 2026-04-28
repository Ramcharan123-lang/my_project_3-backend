package com.studentproject.backend.repository;

import com.studentproject.backend.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, String> {
    List<Message> findByGroupIdOrderByCreatedAtAsc(String groupId);
}
