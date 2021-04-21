package com.chardev.managercash.service;

import com.chardev.managercash.model.Person;
import com.chardev.managercash.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.chardev.managercash.model.Birthday;
import com.chardev.managercash.repository.BirthdayRepository;

@Service
public class BirthdayService {

    PersonService personService;

    @Autowired
    private BirthdayRepository birthdayRepository;

    public List<Birthday> getAll() {
        List<Birthday> birthdays = birthdayRepository.findAll();
        return birthdays;
    }

    public void deleteById(Integer id) {
        birthdayRepository.delete(getById(id));
    }

    // agrego un cumpleaños
    public Birthday add(Birthday birthday) {
        Integer idCumpleaniero = birthday.getCumpleaniero().getId(); // id cumpleañero
        Person cumpleaniero = personService.getById(idCumpleaniero); // veo si existe
        if(cumpleaniero != null){
            // verifico que los invitado agregados sean menos de 11
            if (birthday.getInvitados().size() <= 10) {
                return birthdayRepository.save(birthday);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public Birthday getById(Integer id) {
        return birthdayRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public Birthday update(Birthday _birthday) {
        Optional<Birthday> birthday = birthdayRepository.findById(_birthday.getId());
        if (birthday.isPresent()) {
            return birthdayRepository.save(_birthday);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}