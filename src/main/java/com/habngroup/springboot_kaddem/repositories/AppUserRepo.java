package com.habngroup.springboot_kaddem.repositories;

import com.habngroup.springboot_kaddem.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
