package com.chardev.managercash.repository;

import com.chardev.managercash.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRespository extends JpaRepository<Person,String> {
    
}
