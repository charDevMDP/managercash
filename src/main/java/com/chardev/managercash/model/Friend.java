package com.chardev.managercash.model;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Friend extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String profession;
    private String socialStatus;

    @Override
    public TypePerson typePerson() {
        return TypePerson.FRIEND;
    }
}