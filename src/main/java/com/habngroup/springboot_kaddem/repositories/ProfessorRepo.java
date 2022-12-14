package com.habngroup.springboot_kaddem.repositories;

import com.habngroup.springboot_kaddem.entities.Professor;
import com.habngroup.springboot_kaddem.entities.Specialite;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepo extends JpaRepository<Professor, Long> {
      List<Professor> findProfessorsByDepartmentIdDepart(Long idDepart) ;
      List <Professor> findByFirstNameOrLastNameOrPhoneOrEmailOrSpecialityAndFirstNameIsNotNullAndLastNameIsNotNullAndPhoneIsNotNullAndEmailIsNotNullAndSpecialityIsNotNull
              (String firstName, String lastName, String phone, String email , Specialite speciality );


}
