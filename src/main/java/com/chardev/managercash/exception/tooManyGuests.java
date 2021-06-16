package com.chardev.managercash.exception;

public class tooManyGuests extends RuntimeException  {

    public tooManyGuests(String value)
    {
        super(value+" Ya son demasiado che!");
    }
}
