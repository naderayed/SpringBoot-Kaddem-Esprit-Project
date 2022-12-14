package com.habngroup.springboot_kaddem.services;


import com.habngroup.springboot_kaddem.entities.AppUser;
import com.habngroup.springboot_kaddem.entities.Role;
import com.habngroup.springboot_kaddem.repositories.AppUserRepo;
import com.habngroup.springboot_kaddem.repositories.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@CrossOrigin
public class AppUserService implements IAppUserService, UserDetailsService {
    private final AppUserRepo appUserRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepo.findByUsername(username);
        if(user == null){
            log.error("User not found");
        }
        else {
            log.info("User found");
        }
        Collection<SimpleGrantedAuthority> authorityCollection= new ArrayList<>();
        user.getRoles().forEach(
                role -> {
                    authorityCollection.add(new SimpleGrantedAuthority(role.getNamerole()));
                }
        );
        return new User(user.getUsername(),user.getPassword(),authorityCollection);
    }
    @Override
    public AppUser saveUser(AppUser user) {
        if(!Objects.equals(appUserRepo.findByUsername(user.getUsername()),user)){
            log.info("saving new user {} to database",user.getUsername());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return appUserRepo.save(user);}
        return null;
    }



    @Override
    public Role saveRole(Role role) {
        log.info("saving new Role {} to database",role.getNamerole());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding Role {} to user {}", roleName,username);
        AppUser appUser = appUserRepo.findByUsername(username);
        Role role = roleRepo.findByNamerole(roleName);
        appUser.getRoles().add(role);
        //i don't have to call appUserRepo.save because this class is transactional
        // appUserRepo.save(appUser);

    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching user {}",username);
        return appUserRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all users");
        return appUserRepo.findAll();
    }


}