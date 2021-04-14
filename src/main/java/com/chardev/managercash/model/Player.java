package com.chardev.managercash.model;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Player extends Person {
    
    private Float Peso;
    private Float Altura;
    private Integer Goles;
    private Integer MinutosJugados;
    private Currency currency;
    private Date FechaNacimiento;

    @Override
    public TypePerson typePerson() {
        return TypePerson.PLAYER;
    }


}
