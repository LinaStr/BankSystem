package org.codeacademy.exceptions;

public class FileSaveException extends Exception{
    public FileSaveException() {
        super("Nepavyko išsagoti duomenų");
    }
}
