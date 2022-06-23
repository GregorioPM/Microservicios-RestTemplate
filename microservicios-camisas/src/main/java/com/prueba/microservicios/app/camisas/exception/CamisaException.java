package com.prueba.microservicios.app.camisas.exception;

import org.springframework.http.HttpStatus;

public class CamisaException extends RuntimeException{
    private HttpStatus httpStatus;

    public CamisaException(HttpStatus httpStatus, String mensaje) {
        super(mensaje);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
