package com.studentproject.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@Entity
@Table(name = "Tasks")
@Data
@NoArgsConstructor
public class Task {

    @Id
    @UuidGenerator
    @Column(length = 36, updatable = false, nullable = false)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "varchar(255) default 'pending'")
    private String status = "pending";

    private String marks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignedTo", referencedColumnName = "id")
    @JsonProperty("assignedTo")
    private User assignedToUser;

    @Column(name = "assignedTo", insertable = false, updatable = false)
    @JsonIgnore
    private String assignedTo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    @JsonProperty("projectId")
    private Project project;

    @Column(name = "projectId", insertable = false, updatable = false)
    @JsonIgnore
    private String projectId;

    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    @JsonProperty("_id")
    public String get_id() {
        return id;
    }
}
