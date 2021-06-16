package com.chardev.managercash.controller.web;

import com.chardev.managercash.model.api.CurrencyApi;
import com.chardev.managercash.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/dolar")
    public CurrencyApi getDolar() throws IOException, InterruptedException {
        Float buy = apiService.getDolar();
        CurrencyApi dolar = CurrencyApi.builder().name("dolar").buy(buy).build();
        return dolar;
    }

    @GetMapping("/euro")
    public CurrencyApi getEuro() throws IOException, InterruptedException {
        Float buy = apiService.getEuro();
        CurrencyApi euro = CurrencyApi.builder().name("euro").buy(buy).build();
        return euro;
    }


}
