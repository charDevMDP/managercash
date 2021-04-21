package com.chardev.managercash.service;

import java.util.List;

import com.chardev.managercash.model.Manager;
import com.chardev.managercash.model.Person;
import com.chardev.managercash.model.Player;
import com.chardev.managercash.model.TypePerson;
import com.chardev.managercash.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


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
    public Person getById(Integer id) {
        return personRepository.findById(id)
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    // agregar jugador a la lista de de un manager
    public void addPlayerToPerson(Integer id, Integer idPlayer) {
        Person person = getById(id);
        if(person.typePerson() == TypePerson.MANAGER){ // validado que el tipo es representante.. no lo pude hacer con un typeof
            Person player = getById(idPlayer);

        // parseo persona a representante
        ((Manager) person).getPlayersList().add((Player) player); // ver por el la lista la tiene el manager y no la persona
        personRepository.save(person);
        }

    }

    // eliminar un persona
    public void removePerson(Integer id){
        personRepository.deleteById(id);
    }


}
