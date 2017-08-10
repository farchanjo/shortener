package br.eti.archanjo.shortener.exceptions;

public class MissingArgumentException extends Exception {

    public MissingArgumentException(String message) {
        super(message);
    }
}