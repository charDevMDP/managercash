package com.chardev.managercash.controller.web;

import com.chardev.managercash.model.Birthday;

import com.chardev.managercash.model.Person;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.chardev.managercash.service.BirthdayService;

@RestController
@NoArgsConstructor
@RequestMapping("/birthday")
public class BirthdayController {

    BirthdayService birthdayService;

    @Autowired
    public BirthdayController(BirthdayService birthdayService){
        this.birthdayService = birthdayService;
    }

    // traigo uno
    @GetMapping("/{id}")
    public Birthday getById(@PathVariable Integer id) {
        return birthdayService.getById(id);
    }

    // traigo todos
    @GetMapping
    public List<Birthday> getAll() { return birthdayService.getAll(); }

    // agrego uno
    @PostMapping
    public Birthday addBirthday(@RequestBody Birthday birthday) {
        return birthdayService.add(birthday);
    }

    // elimino uno
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        birthdayService.deleteById(id);
    }

    // actualizo
    @PutMapping
    public Birthday replaceBirthday(@RequestBody Birthday birthday) {
        return birthdayService.update(birthday);
    }

    @GetMapping("listInvited")
    public ResponseEntity<List<Person>> getAllInvited(Pageable pageable) throws IOException, InterruptedException, JSONException {
        Page pageofInvitados= birthdayService.getAllInvited(pageable);
        return response(pageofInvitados);
    }

    @PutMapping("/{idCumpleanitos}/personas/{idInvitado}")
    public void addPersonaToGuest(@PathVariable Integer IdBirthday, @PathVariable Integer idGuest){
        birthdayService.addPersonaToListInvitados(IdBirthday, idGuest);
    }


    private ResponseEntity response(Page page) {
        HttpStatus httpStatus = page.getContent().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.
                status(httpStatus).
                header("X-Total-Count", Long.toString(page.getTotalElements())).
                header("X-Total-Pages", Long.toString(page.getTotalPages())).
                body(page.getContent());
    }

}