package com.studentproject.backend.payload;

import lombok.Data;

@Data
public class SignupRequest {
    private String name;
    private String email;
    private String password;
    private String role;
    private String phone;
    private String year;
    private String branch;
}
