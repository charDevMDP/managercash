package com.chardev.managercash.service;

import java.util.List;
import java.util.Set;

import com.chardev.managercash.model.*;
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
        ((Manager) person).getPlayersList().add((Player) player);
        personRepository.save(person);
        }

    }

    // eliminar un persona
    public void removePerson(Integer id){
        personRepository.deleteById(id);
    }


    // agregar invitado a la lista de invitados
    public void addPersonToInvitados(Integer id, Integer idPerson) {

        // traigo al cumpleaniero
        Person person = getById(id);

        // verifico si el cumpleanero es Guillermo xD
        if(person.getName() == "Guillermo"){

            // traigo al amigo a invitar
            Person friend = getById(idPerson);

            // trago el cumple del cumpleaniero
            Birthday cumple = (Birthday) person.getBirthdaysList();

            // traigo los invitados del cumple
            Set<Person> invitados = cumple.getInvitados();

            // guardo la cant de invitados
            Integer cantInvitados = invitados.size();

            // veo que sean menos de 11
            if(cantInvitados <= 10){

                // agrego amigo a los invitados
                invitados.add((Friend) friend);
            }

            // guardo los cambios de la persona
            personRepository.save(person);
        }
    }

}
