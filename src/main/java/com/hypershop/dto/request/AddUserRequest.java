package com.hypershop.dto.request;


import lombok.Data;

@Data
public class AddUserRequest {
    private String name;
    private String role;
    private String mobile;
    private String email;
    private String password;
}
