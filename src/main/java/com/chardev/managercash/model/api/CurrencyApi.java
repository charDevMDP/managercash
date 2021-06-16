package com.chardev.managercash.model.api;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyApi {

    @SerializedName("nombre")
    private String name;

    @SerializedName("compra")
    private Float buy;

}
