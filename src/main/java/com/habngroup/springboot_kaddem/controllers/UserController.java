package com.habngroup.springboot_kaddem.controllers;


import com.habngroup.springboot_kaddem.entities.AppUser;
import com.habngroup.springboot_kaddem.entities.Role;
import com.habngroup.springboot_kaddem.services.AppUserService;
import com.habngroup.springboot_kaddem.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class UserController {
    private final AppUserService appUserService;
    private final RoleService roleService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AppUser>> getUsers(){
        return ResponseEntity.ok().body(appUserService.getUsers());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AppUser> getUser(@PathVariable("username") String username){
        return ResponseEntity.ok().body(appUserService.getUser(username));
    }

    @PostMapping("/user/save")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AppUser addUser(@RequestBody AppUser appuser){
        Role role_user = roleService.setUserRole();
        appuser.getRoles().add(role_user);
        return appUserService.saveUser(appuser);
    }

    @PostMapping("/role/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        URI  uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addRoleToUser(@RequestParam("username") String username,@RequestParam("role") String role){
        appUserService.addRoleToUser(username, role );



    }



}
