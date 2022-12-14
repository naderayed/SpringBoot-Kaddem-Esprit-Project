package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.Role;
import com.habngroup.springboot_kaddem.repositories.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;

    public Role setUserRole(){
       return roleRepo.findByNamerole("ROLE_USER");
    }
}
