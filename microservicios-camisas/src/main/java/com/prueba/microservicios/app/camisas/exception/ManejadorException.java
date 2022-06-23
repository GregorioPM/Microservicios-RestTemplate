package com.prueba.microservicios.app.camisas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ManejadorException {

    private static final Map<String, Integer> STATUS = new HashMap<>();


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<MensajeError> AllExceptions(HttpServletRequest request, Exception exception) {
        ResponseEntity<MensajeError> resultado;

        String excepcion = exception.getClass().getSimpleName();
        String mensaje = exception.getMessage();
        Integer codigo = getStatus(exception);

        if (codigo == null) {
            codigo = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }

        MensajeError error = new MensajeError();
            error.setMensaje(mensaje);
            error.setExcepcion(excepcion);
            error.setUrl(request.getRequestURI());
            error.setStatus(codigo);
            error.setOperacion(request.getMethod());
        resultado = new ResponseEntity<>(error, HttpStatus.valueOf(codigo));
        exception.printStackTrace();
        return resultado;
    }

    private Integer getStatus(Exception e) {
        if (e instanceof CamisaException) {
            CamisaException ex = (CamisaException) e;
            if (ex.getHttpStatus() != null) {
                return ex.getHttpStatus().value();
            }
        }
        return STATUS.get(e.getClass().getSimpleName());
    }

}
