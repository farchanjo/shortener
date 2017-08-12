package br.eti.archanjo.velociraptor.exceptions;

public class AlreadyOnLimitException extends Exception {

    public AlreadyOnLimitException(String message) {
        super(message);
    }
}