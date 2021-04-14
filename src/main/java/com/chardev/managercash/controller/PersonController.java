package com.chardev.managercash.controller;

import java.util.List;
import java.util.Optional;

import com.chardev.managercash.model.Person;
import com.chardev.managercash.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    // * GET/person
    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }

    // * GET/person/{id}
    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable String id) {
        return personService.getByID(id);
    }

    // * POST/person
    @PostMapping
    public void addPersona(@RequestBody Person person) {
        personService.add(person);
    }

    // * PUT/person/{id}/jugadores/{idJugador}
    @PutMapping("/{id}/playes/{idPlayer}")
    public void addPlayerToPerson(@PathVariable String id, @PathVariable String idPlayer) {
        personService.addPlayerToPerson(id,idPlayer);
    }

    // * DEL /person/{id}
    @DeleteMapping("/{id}")
    public void removePerson(@PathVariable String id){
        personService.removePerson(id);
    }
}
