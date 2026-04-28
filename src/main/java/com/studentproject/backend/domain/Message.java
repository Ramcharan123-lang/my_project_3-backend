package com.studentproject.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@Entity
@Table(name = "Messages")
@Data
@NoArgsConstructor
public class Message {

    @Id
    @UuidGenerator
    @Column(length = 36, updatable = false, nullable = false)
    private String id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "senderId", referencedColumnName = "id")
    @JsonProperty("senderId")
    private User sender;

    @Column(name = "senderId", insertable = false, updatable = false)
    @JsonIgnore
    private String senderId;

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
