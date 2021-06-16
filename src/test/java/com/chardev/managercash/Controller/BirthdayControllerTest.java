package com.chardev.managercash.Controller;

import com.chardev.managercash.controller.web.BirthdayController;
import com.chardev.managercash.model.Person;
import com.chardev.managercash.model.TypePerson;
import com.chardev.managercash.model.dto.PersonPayDTO;
import com.chardev.managercash.service.BirthdayService;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BirthdayControllerTest {

    BirthdayController birthdayController;
    BirthdayService birthdayService;

    @BeforeEach
    public void setUp(){
        this.birthdayService = mock(BirthdayService.class);
        this.birthdayController = new BirthdayController(birthdayService);
    }

    @Test
    public  void getAllInvitadosOK() throws IOException, InterruptedException, JSONException {
        //given
        Pageable pageable = PageRequest.of(1, 10);
        Page<PersonPayDTO> mockedPage = mock(Page.class);

        List<Person> listaInvitados = List.of(new Person() {
            @Override
            public TypePerson typePerson() {
                return TypePerson.PLAYER;
            }
        });

        when(birthdayService.getAllInvited(pageable)).thenReturn(mockedPage);

        //when

        ResponseEntity<List<Person>> response = birthdayController.getAllInvited(pageable);

        //then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }


}
