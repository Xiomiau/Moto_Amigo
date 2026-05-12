package com.mycompany.motoamigonegocio;

/**
 * Excepción personalizada para manejar las violaciones a las reglas de negocio.
 */
public class NegocioException extends Exception {


    public NegocioException() {
        super();
    }


    public NegocioException(String message) {
        super(message);
    }


    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
}