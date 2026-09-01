// service/exception/EmailYaRegistradoException.java
package org.uned.practicatw.service.exception;

/** Lanzada por {@code UsuarioServiceImpl.crear(Usuario)} cuando el email ya existe en la tabla {@code usuarios}. */
public class EmailYaRegistradoException extends RuntimeException {

    public EmailYaRegistradoException(String email) {
        super("El email ya existe: " + email);
    }

    public EmailYaRegistradoException() {
        super("El email ya existe");
    }
}