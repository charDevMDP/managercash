package com.chardev.managercash.service;

import com.chardev.managercash.controller.web.PersonController;
import com.chardev.managercash.exception.tooManyGuests;
import com.chardev.managercash.model.Person;
import com.chardev.managercash.model.Player;
import com.chardev.managercash.model.TypeCurrency;
import com.chardev.managercash.model.dto.PersonPayDTO;
import com.chardev.managercash.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.chardev.managercash.model.Birthday;
import com.chardev.managercash.repository.BirthdayRepository;

@Service
public class BirthdayService {

    PersonService personService;


    private BirthdayRepository birthdayRepository;
    private ApiService apiService;

    @Autowired
    public BirthdayService(BirthdayRepository birthdayRepository, ApiService apiService){
        this.birthdayRepository = birthdayRepository;
        this.apiService = apiService;
    }


    public Birthday getById(Integer id) {
        return birthdayRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<Birthday> getAll() {
        return birthdayRepository.findAll();
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


    public void deleteById(Integer id) {
        birthdayRepository.delete(getById(id));
    }


    public Birthday update(Birthday _birthday) {
        Optional<Birthday> birthday = birthdayRepository.findById(_birthday.getId());
        if (birthday.isPresent()) {
            return birthdayRepository.save(_birthday);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Page<PersonPayDTO> getAllInvited(Pageable pageable) throws IOException, InterruptedException {

        List<Birthday> cumples = birthdayRepository.findAll();

        List<PersonPayDTO> pagos = new ArrayList();
        Float dolar = apiService.getDolar();
        Float euro = apiService.getEuro();
        System.out.println("Dolar: " + dolar);
        System.out.println("Euro: " + euro);

        for(Birthday cumple: cumples){

            for(Person invitado: cumple.getInvitados()){
                if(invitado instanceof Player){
                    PersonPayDTO datosDeInvitado = new PersonPayDTO();

                    datosDeInvitado.setName(invitado.getName() + " " + invitado.getLastname());
                    datosDeInvitado.setCurrency(((Player) invitado).getCurrency().getTypeCurrency());

                    datosDeInvitado.setAmount((datosDeInvitado.getCurrency() == TypeCurrency.DOLAR) ? 25000 / dolar:25000 / euro);
                    pagos.add(datosDeInvitado);
                }
            }
        }
        return new PageImpl<>(pagos);

    }

    public void addPersonaToListInvitados(Integer idBirthday, Integer idGuest) {

        Birthday cumpli = getById(idBirthday);
        PersonController personaController= new PersonController();
        Person invitado = personaController.getPersonById(idGuest);
        if(cumpli.getInvitados().stream().count() < 10){
            cumpli.getInvitados().add(invitado);
            birthdayRepository.save(cumpli);
        }
        else
        {
            throw  new tooManyGuests("No hay espacio para otro invitado");
        }

    }
}