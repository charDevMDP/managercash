package com.chardev.managercash.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.chardev.managercash.model.Birthday;

@Repository
public interface BirthdayRepository extends JpaRepository<Birthday, Integer> {
}