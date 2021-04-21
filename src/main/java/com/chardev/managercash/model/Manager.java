package com.chardev.managercash.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Manager extends Person{
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private List<Player> playersList;

    private Integer pesoDeLaBoveda;
    private Integer montoTotal;

    @Override
    public TypePerson typePerson() {
        return TypePerson.MANAGER;
    } 

    public Double countMontoTotalInPesos(){

        double monto = 0;
        for (Player player : playersList){
            monto += player.getCurrency().getMonto() * player.getCurrency().getCurrency().getUnidInPesos();
        }
        return monto;
    }

    public Double calcPesoBoveda(){
        return Math.floor(montoTotal/100);
    }
}
