package com.studentproject.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@Entity
@Table(name = "Submissions")
@Data
@NoArgsConstructor
public class Submission {

    @Id
    @UuidGenerator
    @Column(length = 36, updatable = false, nullable = false)
    private String id;

    private String fileUrl;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime submittedAt = LocalDateTime.now();

    private String grade;

    @Column(columnDefinition = "varchar(255) default 'pending'")
    private String status = "pending";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    @JsonProperty("projectId")
    private Project project;

    @Column(name = "projectId", insertable = false, updatable = false)
    @JsonIgnore
    private String projectId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupId", referencedColumnName = "id")
    @JsonProperty("groupId")
    private Group group;

    @Column(name = "groupId", insertable = false, updatable = false)
    @JsonIgnore
    private String groupId;

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
