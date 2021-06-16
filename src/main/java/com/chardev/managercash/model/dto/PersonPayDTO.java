package com.chardev.managercash.model.dto;

import com.chardev.managercash.model.TypeCurrency;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonPayDTO {

    private String name;
    private TypeCurrency currency;
    private Float amount;
}
