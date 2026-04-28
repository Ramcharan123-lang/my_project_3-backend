package com.studentproject.backend.controller;

import com.studentproject.backend.domain.User;
import com.studentproject.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(@PathVariable String role) {
        return userService.getUsersByRole(role);
    }
    
    @GetMapping("/group/{groupId}")
    public List<User> getGroupMembers(@PathVariable String groupId) {
        return userService.getGroupMembers(groupId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User userDetails) {
        return userService.getUserById(id).map(user -> {
            if (userDetails.getGroupId() != null) user.setGroup(userDetails.getGroup());
            if (userDetails.getRole() != null) user.setRole(userDetails.getRole());
            return ResponseEntity.ok(userService.saveUser(user));
        }).orElse(ResponseEntity.notFound().build());
    }
}
