package com.chardev.managercash.Controller;

import com.chardev.managercash.controller.web.ApiController;
import com.chardev.managercash.model.Player;
import com.chardev.managercash.service.ApiService;
import com.google.gson.JsonArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApiControllerTest {

    ApiService apiService;
    ApiController apiController;

    @BeforeEach
    public void setUp(){
        this.apiService = mock(ApiService.class);
        this.apiController = new ApiController(apiService);
    }

   @Test
    public void getPlayerOK() throws IOException, InterruptedException {

       JsonArray jsonArray = new JsonArray();
       Player player = Player.builder().build();
       List<Player> players = List.of(player);
       when(apiService.getPromises()).thenReturn(jsonArray);


       List<Player> result = apiController.getPlayers();

       assertEquals(players,result);

   }





}
