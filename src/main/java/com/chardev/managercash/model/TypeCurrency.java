package com.chardev.managercash.model;

public enum TypeCurrency {
    DOLAR("dolares", 99.00),
    EURO("euros", 115.00);

    private String description;
    private Double unidInPesos;

    TypeCurrency (String descripcion, Double unidadEnPesos){
        this.description = descripcion;
        this.unidInPesos = unidadEnPesos;
    }

    public static TypeCurrency find(final String value){
        for(TypeCurrency c : values()){
            if (c.toString().equalsIgnoreCase(value))
                return c;
        }
        throw new IllegalArgumentException(String.format("Invalid TypeCurrency: %s", value));
    }

    public String getDescription() {
        return description;
    }

    public Double getUnidInPesos(){
        return unidInPesos;
    }
}
