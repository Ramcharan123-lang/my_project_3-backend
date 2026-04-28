package com.studentproject.backend.repository;

import com.studentproject.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(String role);
    List<User> findByGroupId(String groupId);
    boolean existsByEmail(String email);
}
