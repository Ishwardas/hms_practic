package com.jwt8.payload.user;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}
