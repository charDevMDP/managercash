package com.chardev.managercash.model;

public enum TypeCurrency {
    DOLARES("dolares", 99),
    EUROS("euros", 115);

    private String description;
    private Integer unidInPesos;

    TypeCurrency (String descripcion, Integer unidadEnPesos){
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

    public Integer getUnidInPesos(){
        return unidInPesos;
    }
}
