package com.chardev.managercash.repository;

import com.chardev.managercash.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.chardev.managercash.model.Payment;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Person, Integer> {

    @Query(value= "SELECT * FROM BIRTHDAY WHERE fecha = ?1 AND cumpleaniero = ?2 ", nativeQuery = true)
    List<Person> findAllGuests(LocalDate fecha, Integer id);

}