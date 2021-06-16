package com.chardev.managercash.Service;

import com.chardev.managercash.controller.web.ApiController;
import com.chardev.managercash.service.ApiService;
import com.google.gson.JsonArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ApiServiceTest {

    ApiService apiService;

    @BeforeEach
    public void setUp(){
        this.apiService = new ApiService();
    }

    @Test
    public void getPlayers() throws IOException, InterruptedException {
        JsonArray jsonArray = new JsonArray();
        JsonArray response = apiService.getPromises();

        assertEquals(jsonArray,response);

    }

}
