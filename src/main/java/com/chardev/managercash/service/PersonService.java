package com.chardev.managercash.service;

import java.util.List;
import java.util.Optional;

import com.chardev.managercash.model.Manager;
import com.chardev.managercash.model.Person;
import com.chardev.managercash.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonService {

    private PersonRepository personRepository;


    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    // trae todas las personas
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    // agrega persona
    public void add(Person persona) {
        personRepository.save(persona);
    }

    // busca persona por id
    public Optional<Person> getByID(String idPlayer) {
        return personRepository.findById(idPlayer);
    }

    // agregar jugador a la lista de de un manager
    public void addPlayerToPerson(String id, String idPlayer) {
        Optional<Person> person = getByID(id);
        Optional<Person> player = getByID(idPlayer);
        person.getPlayerList().add(player); // ver por el la lista la tiene el manager y no la persona
        personRepository.save(person);
    }

    public void removePerson(String id){
        personRepository.deleteById(id);
    }


}
