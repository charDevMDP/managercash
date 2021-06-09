package com.chardev.managercash.controller.web;

import com.chardev.managercash.model.api.Dolar;
import com.chardev.managercash.model.api.Euro;
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
    public Dolar getDolar() throws IOException, InterruptedException {
        return apiService.getDolar();
    }

    @GetMapping("/euro")
    public Euro getEuro() throws IOException, InterruptedException {
        return apiService.getEuro();
    }


}
