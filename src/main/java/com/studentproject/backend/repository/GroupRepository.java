package com.studentproject.backend.repository;

import com.studentproject.backend.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, String> {
}
