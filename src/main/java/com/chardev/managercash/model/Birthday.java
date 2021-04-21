package com.chardev.managercash.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;
import com.chardev.managercash.model.Person;

@Data
@Entity
@NoArgsConstructor
public class Birthday {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "El cumpleaños debe tener una fecha")
    private LocalDate fecha;

    @NotBlank(message = "Debe pertenecer a un cumpleañero")
    private Person cumpleaniero;

    private Set<Person> invitados;

}