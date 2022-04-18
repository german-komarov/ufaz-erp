package com.pages.ufazerp.domain;

import com.pages.ufazerp.util.constants.Role;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends User {

    @Override
    public Role getRole() {
        return Role.ROLE_ADMIN;
    }
}
