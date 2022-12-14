package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.AppUser;
import com.habngroup.springboot_kaddem.entities.Role;

import java.util.List;

public interface IAppUserService {
    AppUser saveUser(AppUser user);
    Role saveRole(Role user);
    void addRoleToUser(String username, String roleName);
    AppUser getUser (String username);
    List<AppUser> getUsers();
}

