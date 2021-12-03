package com.example.security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

import static com.example.security.UserPermissions.*;

@AllArgsConstructor
@Getter
public enum UserRole {
    CLIENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(CLIENT_READ, CLIENT_WRITE));

    private final Set<UserPermissions> permissions;
}
