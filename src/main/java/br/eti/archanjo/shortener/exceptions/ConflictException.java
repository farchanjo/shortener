package br.eti.archanjo.shortener.exceptions;

public class ConflictException extends Exception {

    public ConflictException(String message) {
        super(message);
    }
}