package com.habngroup.springboot_kaddem.repositories;

import com.habngroup.springboot_kaddem.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByNamerole(String roleName);
}
