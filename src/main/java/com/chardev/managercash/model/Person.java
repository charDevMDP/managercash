package com.chardev.managercash.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import org.springframework.data.annotation.AccessType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "typePerson", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Manager.class, name = "MANAGER"),
        @JsonSubTypes.Type(value = Player.class, name = "PLAYER"),
        @JsonSubTypes.Type(value = Friend.class, name = "FRIEND")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract  class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(unique = true)
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastname;

    @AccessType(AccessType.Type.PROPERTY)
    public abstract TypePerson typePerson();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Birthday> birthdaysList;


}
