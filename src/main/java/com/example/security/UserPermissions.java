package com.example.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserPermissions {
    CLIENT_READ("client:read"),
    CLIENT_WRITE("client:write");

    private final String permissions;
}
