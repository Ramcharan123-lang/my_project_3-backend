package com.studentproject.backend.service;

import com.studentproject.backend.domain.Group;
import com.studentproject.backend.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Optional<Group> getGroupById(String id) {
        return groupRepository.findById(id);
    }

    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }
}
