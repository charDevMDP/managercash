package com.chardev.managercash.model;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Manager extends Person{
    
    private List<Player> players;
    private Integer pesoDeLaBoveda;
    private Integer montoTotal;

    @Override
    public TypePerson typePerson() {
        return TypePerson.MANAGER;
    } 
}
