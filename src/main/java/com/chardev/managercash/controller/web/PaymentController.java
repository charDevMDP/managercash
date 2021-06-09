package com.chardev.managercash.controller.web;

import com.chardev.managercash.model.Person;
import com.chardev.managercash.model.dto.PersonPayDTO;
import com.chardev.managercash.service.PaymentService;
import com.chardev.managercash.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PersonPayDTO>> getAllGuests(@RequestParam(value = "size", defaultValue = "20") Integer size,
                                               @RequestParam(value = "page", defaultValue = "0") Integer page
    ){
        Page<PersonPayDTO> pagePerson = paymentService.getAllGuests(size, page);
        return response(pagePerson);
    }

    private ResponseEntity response(Page page) {
        HttpStatus httpStatus = page.getContent().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.
                status(httpStatus).
                header("X-Total-Count", Long.toString(page.getTotalElements())).
                header("X-Total-Pages", Long.toString(page.getTotalPages())).
                body(page.getContent());
    }

}


