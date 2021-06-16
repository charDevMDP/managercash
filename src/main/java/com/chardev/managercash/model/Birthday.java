package com.chardev.managercash.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Person cumpleaniero;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="idPersona", nullable = false)
    private List<Person> invitados;

    public void setInvitados(List<Person> invitados) {
        this.invitados = invitados;
    }

}