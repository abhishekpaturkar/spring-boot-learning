package com.abhishek.module5.dto;

import com.abhishek.module5.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDTO {
    private String email;
    private String password;
    private String name;
    // Not ideal for production to have roles on signup, but for learning purposes, we can have it here
    private Set<Role> roles;
}
