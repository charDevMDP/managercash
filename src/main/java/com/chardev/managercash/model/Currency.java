package com.chardev.managercash.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Currency {

    @Id
    private String id;
    
    private String currency;
    
    private Integer monto;
    
}
