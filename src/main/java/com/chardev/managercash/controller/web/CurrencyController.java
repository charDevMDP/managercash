package com.chardev.managercash.controller.web;

import com.chardev.managercash.service.CurrencyService;

import java.util.List;
import java.util.Optional;

import com.chardev.managercash.model.Currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    
    @Autowired
    CurrencyService currencyService;

    // * GET/current
    @GetMapping("/")
    public List<Currency> getAll() {
        return currencyService.getAll();
    }

    // * GET/currency/{id}
    @GetMapping("/{id}")
    public Optional<Currency> getPersonById(@PathVariable String id) {
        return currencyService.getByID(id);
    }

    // * POST/currency
    @PostMapping
    public void addCurrency(@RequestBody Currency currency) {
        currencyService.addCurrent(currency);
    }


    // * DEL /currency/{id}
    @DeleteMapping("/{id}")
    public void removeCurrent(@PathVariable String id){
        currencyService.removeCurrency(id);
    }

}
