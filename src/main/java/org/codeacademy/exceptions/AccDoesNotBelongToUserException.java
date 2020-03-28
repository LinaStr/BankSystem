package org.codeacademy.exceptions;

public class AccDoesNotBelongToUserException extends Exception{
    public AccDoesNotBelongToUserException() {
        super("Ši sąskaita Jums nepriklauso \n  VAGIS SUKNISTAS, SKAMBINAM POLICIJAI");
    }
}
