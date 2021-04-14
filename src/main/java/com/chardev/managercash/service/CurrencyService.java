package com.chardev.managercash.service;

import java.util.List;
import java.util.Optional;

import com.chardev.managercash.model.Currency;
import com.chardev.managercash.repository.CurrencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
  
    @Autowired
    CurrencyRepository currencyRepository;

    // agregar una divisa
    public void addCurrent(Currency currency){
        currencyRepository.save(currency);
    }

    // lista todas las divisas
    public List<Currency> getAll(){
        return currencyRepository.findAll();
    }

    // busca un divida por id
    public Optional<Currency> getByID(String idCurrency){
        return currencyRepository.findById(idCurrency);
    }

    // eliminar una divisa
    public void removeCurrency(String id){
        currencyRepository.deleteById(id);
    }

    

}
