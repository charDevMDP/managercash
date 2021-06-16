package com.chardev.managercash.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Player extends Person {
    
    @PositiveOrZero(message = "Debe tener un peso mayor a cero")
    private Float Peso;

    @NotNull(message = "Que? no esta a la altura?")
    private Float Altura;

    @PositiveOrZero(message = "es malo pero no tanto, goles de 0 pa arriba")
    private Integer Goles;

    @PositiveOrZero(message = "El valor solo puede ser positivo o cero")
    private Integer MinutosJugados;

    private LocalDate FechaNacimiento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Override
    public TypePerson typePerson() {
        return TypePerson.PLAYER;
    }


}
