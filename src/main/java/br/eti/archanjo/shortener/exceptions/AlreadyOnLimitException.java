package br.eti.archanjo.shortener.exceptions;

public class AlreadyOnLimitException extends Exception {

    public AlreadyOnLimitException(String message) {
        super(message);
    }
}