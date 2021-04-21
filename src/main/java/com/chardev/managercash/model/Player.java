package com.chardev.managercash.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Player extends Person {
    
    @PositiveOrZero(message = "Debe tener un peso mayor a cero")
    private Float Peso;
    
    private Float Altura;
    private Integer Goles;
    private Integer MinutosJugados;
    private LocalDate FechaNacimiento;

    @OneToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Override
    public TypePerson typePerson() {
        return TypePerson.PLAYER;
    }


}
