package com.chardev.managercash.controller.web;

import com.chardev.managercash.model.Person;
import com.chardev.managercash.model.Player;
import com.chardev.managercash.model.api.CurrencyApi;
import com.chardev.managercash.model.dto.PersonPayDTO;
import com.chardev.managercash.service.ApiService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {


    private ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

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

    @GetMapping("/players")
    public List<Player> getPlayers() throws  IOException, InterruptedException {

        JsonArray playersApi = apiService.getPromises();

        List<Player> promises = new ArrayList();

        for (JsonElement promise : playersApi) {
            if(!(promise.getAsJsonObject().get("height") <= 180)){
                Player player = new Player();

                player.setAltura( Integer.parseInt( promise.getAsJsonObject().get("height") ));
                player.setFechaNacimiento( LocalDate.parse(promise.getAsJsonObject().get("birthday")));
                player.setName(promise.getAsJsonObject().get("firtname"));
                player.setLastname(promise.getAsJsonObject().get("lastname"));

                promises.add(player);

            }
        }
        return promises;
    }


}
