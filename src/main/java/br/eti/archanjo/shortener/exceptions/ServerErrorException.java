package br.eti.archanjo.shortener.exceptions;

public class ServerErrorException extends Exception {

    public ServerErrorException(String message) {
        super(message);
    }
}