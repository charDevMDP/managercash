package com.chardev.managercash.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Manager extends Person{

    @Id
    private Integer id;

    @PositiveOrZero
    private Integer pesoDeLaBoveda;

    @PositiveOrZero
    private Double montoTotal;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private List<Player> playersList;

    @Override
    public TypePerson typePerson() {
        return TypePerson.MANAGER;
    } 

    public Double countMontoTotalInPesos(){

        double monto = 0;
        for (Player player : playersList){
            monto += player.getCurrency().getMonto() * player.getCurrency().getTypeCurrency().getUnidInPesos();
        }
        return monto;
    }

    public Double calcPesoBoveda(){
        Double montoT = getMontoTotal();
        return Math.floor(montoT/100);
    }
}
