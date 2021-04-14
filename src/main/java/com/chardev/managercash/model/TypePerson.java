package com.chardev.managercash.model;

public enum TypePerson {
    PLAYER("Player"),
    MANAGER("Manager");


    private String descripcion;

    TypePerson(String descripcion) {
        this.descripcion = descripcion;
    }

    public static TypePerson find(final String value) {
        for (TypePerson v : values()) {
            if (v.toString().equalsIgnoreCase(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid TypeVehiculo: %s", value));
    }

    public String getDescripcion() {
        return descripcion;
    }

}
