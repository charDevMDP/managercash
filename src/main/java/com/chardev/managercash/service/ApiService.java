package com.chardev.managercash.service;

import com.chardev.managercash.model.api.CurrencyApi;
import com.google.gson.JsonParser;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ApiService {

    @CircuitBreaker(name = "dolar", fallbackMethod = "fallbackCurrency")
    public Float getDolar() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=dolar"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request,HttpResponse.BodyHandlers.ofString());

        String value = JsonParser.parseString(response.body())
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("casa")
                .getAsJsonObject()
                .get("compra")
                .getAsString();

        return Float.valueOf(value.replace(",","."));
    }

    @CircuitBreaker(name = "euro", fallbackMethod = "fallbackCurrency")
    public Float getEuro() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=genedolar&opc=ta"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request,HttpResponse.BodyHandlers.ofString());

        String value = JsonParser.parseString(response.body())
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("dolar")
                .getAsJsonObject()
                .get("compra")
                .getAsString();

        return Float.valueOf(value.replace(",","."));
    }

    public Float fallbackCurrency(final Throwable t) {
        log.info(" ================++=====+++====+++=====++==Fallback cause, {}", t.toString());
        return -1F;
    }




}