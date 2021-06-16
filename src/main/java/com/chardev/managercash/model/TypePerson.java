package com.chardev.managercash.model;

public enum TypePerson {
    PLAYER("player"),
    MANAGER("manager"),
    FRIEND("friend");


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
