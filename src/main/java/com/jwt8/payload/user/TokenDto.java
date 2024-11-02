package com.jwt8.payload.user;

import lombok.Data;

@Data
public class TokenDto {
    private String token;
    private String type;
}
