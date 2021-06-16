package com.chardev.managercash.service;

import java.util.List;
import java.util.Set;
import java.util.TooManyListenersException;

import com.chardev.managercash.controller.web.BirthdayController;
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


    /* agregar invitado a la lista de invitados
    public void addPersonToInvitados(Integer id, Integer idPerson) {

        Person person = getById(id); // traigo al cumpleaniero

        if(person.getName() == "Guillermo"){ // verifico si el cumpleanero es Guillermo xD

            Person friend = getById(idPerson); // traigo al amigo a invitar

            Birthday cumple = (Birthday) person.getBirthdaysList();  // trago el cumple del cumpleaniero

            List<Person> invitados = cumple.getInvitados();  // traigo los invitados del cumple

            Integer cantInvitados = invitados.size();  // guardo la cant de invitados

            if(cantInvitados <= 10){  // veo que sean menos de 11
                invitados.add((Friend) friend);  // agrego amigo a los invitados
            }

            personRepository.save(person);  // guardo los cambios de la persona
        }
    }

     */

    public void addFriendToGuests(Integer idPerson, Integer idBirthday) throws TooManyListenersException {
        Person person = getById(idPerson);
        BirthdayController birthdayController= new BirthdayController();
        Birthday birthday = birthdayController.getById(idBirthday);

        if(person instanceof Friend){
            if(birthday.getInvitados().stream().count()<10){
                personRepository.save(person);
            }else{
                throw new TooManyListenersException("Lo siento.. ");
            }
            throw new IllegalArgumentException("No es un amigo che");
        }
    }

}
