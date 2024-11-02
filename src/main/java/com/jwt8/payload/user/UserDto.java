package com.jwt8.payload.user;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String username;
    private String email;
    private String password;
    private String role;
}
