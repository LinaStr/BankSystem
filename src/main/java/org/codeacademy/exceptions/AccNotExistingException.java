package org.codeacademy.exceptions;

public class AccNotExistingException extends Exception{
    public AccNotExistingException() {
        super("Toks sąskaitos numeris neegzistuoja");
    }
}
