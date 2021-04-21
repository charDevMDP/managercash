package com.chardev.managercash.controller;

import com.chardev.managercash.model.Birthday;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.chardev.managercash.service.BirthdayService;

@RestController
@RequestMapping("/birthday")
public class BirthdayController {

    @Autowired
    BirthdayService birthdayService;

    // agrego uno
    @PostMapping
    public Birthday addBirthday(@RequestBody Birthday birthday) {
        return birthdayService.add(birthday);
    }

    // traigo todos
    @GetMapping
    public List<Birthday> getAll() {
        return birthdayService.getAll();
    }

    // traigo uno
    @GetMapping("/{id}")
    public Birthday getById(@PathVariable Integer id) {
        return birthdayService.getById(id);
    }

    // actualizo
    @PutMapping
    public Birthday replaceBirthday(@RequestBody Birthday birthday) {
        return birthdayService.update(birthday);
    }

    // elimino uno
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        birthdayService.deleteById(id);
    }

}