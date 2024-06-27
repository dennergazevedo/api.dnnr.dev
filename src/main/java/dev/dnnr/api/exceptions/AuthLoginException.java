package dev.dnnr.api.exceptions;

public class AuthLoginException extends RuntimeException{

    public AuthLoginException() { super("Não autorizado"); }
}
