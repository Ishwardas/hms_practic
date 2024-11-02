package com.jwt8.payload.user;

import lombok.Data;

@Data
public class RUserDto {
    private String username;
    private String name;
    private String email;
    private String role;

}
