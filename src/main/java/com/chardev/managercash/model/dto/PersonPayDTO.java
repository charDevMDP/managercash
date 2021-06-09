package com.chardev.managercash.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonPayDTO {

    private String name;
    private String currency;
    private String amount;
}
