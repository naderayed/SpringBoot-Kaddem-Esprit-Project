package com.habngroup.springboot_kaddem.repositories;

import com.habngroup.springboot_kaddem.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findClubByNomClub(String nomClub);
}
