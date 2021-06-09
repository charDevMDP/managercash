package com.chardev.managercash.service;

import com.chardev.managercash.model.Person;
import com.chardev.managercash.model.dto.PersonPayDTO;
import com.chardev.managercash.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PersonRepository personRepository;

    public Page<PersonPayDTO> getAllGuests(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return  personRepository.findAll(pageable);
    }

}