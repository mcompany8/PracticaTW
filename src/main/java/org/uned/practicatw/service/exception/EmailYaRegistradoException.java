package org.uned.practicatw.service.exception;

public class EmailYaRegistradoException extends RuntimeException {

    public EmailYaRegistradoException(String email) {
        super("El email ya existe: " + email);
    }

    public EmailYaRegistradoException() {
        super("El email ya existe");
    }
}
