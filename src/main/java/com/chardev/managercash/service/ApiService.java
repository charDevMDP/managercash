package com.chardev.managercash.service;

import com.chardev.managercash.model.api.Dolar;
import com.chardev.managercash.model.api.Euro;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ApiService {

    @CircuitBreaker(name = "dolar", fallbackMethod = "fallbackDolar")
    public Dolar getDolar() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=dolar"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request,HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body(), Dolar.class);
    }

    @CircuitBreaker(name = "euro", fallbackMethod = "fallbackEuro")
    public Euro getEuro() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=genedolar&opc=ta"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request,HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body(), Euro.class);
    }

    public Dolar fallbackDolar(final Throwable t) {
        log.info("Fallback cause, {}", t.toString());
        return Dolar.builder().build();
    }

    public Euro fallbackEuro(final Throwable t) {
        log.info("Fallback cause, {}", t.toString());
        return Euro.builder().build();
    }


}