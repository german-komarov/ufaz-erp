package com.pages.ufazerp.util.constants;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, TEACHER, STUDENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
