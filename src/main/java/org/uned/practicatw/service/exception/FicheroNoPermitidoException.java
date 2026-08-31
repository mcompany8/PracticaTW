package org.uned.practicatw.service.exception;

public class FicheroNoPermitidoException extends RuntimeException {
    public FicheroNoPermitidoException(String fichero) {
        super("El fichero " + fichero + " tiene una extensión no permitida.");
    }

    public FicheroNoPermitidoException() {
        super("El fichero tiene una extansión no permitida.");
    }
}
