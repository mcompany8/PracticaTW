package org.uned.practicatw.service.exception;

public class FicheroInvalidoException extends RuntimeException {
    public FicheroInvalidoException(String fileName) {
        super("El fichero " + fileName + " está vacío o no existe.");
    }

    public FicheroInvalidoException() {
        super("El fichero está vacío o no existe.");
    }
}
