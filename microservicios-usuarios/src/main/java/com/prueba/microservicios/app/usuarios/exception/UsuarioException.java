package com.prueba.microservicios.app.usuarios.exception;

import org.springframework.http.HttpStatus;

public class UsuarioException extends RuntimeException{
    private HttpStatus httpStatus;

    public UsuarioException(HttpStatus httpStatus, String mensaje) {
        super(mensaje);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
