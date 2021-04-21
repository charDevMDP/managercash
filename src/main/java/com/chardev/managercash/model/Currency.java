package com.chardev.managercash.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Currency {

    @Id
    @GeneratedValue
    private String id;
    
    private TypeCurrency currency;
    
    @Positive
    @NotNull
    private Integer monto;
    
    @OneToOne(mappedBy = "currency")
    private Player player;

}
