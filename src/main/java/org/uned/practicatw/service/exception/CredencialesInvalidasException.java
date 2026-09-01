// service/exception/CredencialesInvalidasException.java
package org.uned.practicatw.service.exception;

/** Excepción sin uso actual — {@code AuthServiceImpl}/{@code LoginCommand} manejan el login fallido devolviendo un {@code Optional.empty()}, no lanzando esta excepción. */
public class CredencialesInvalidasException extends RuntimeException {

    public CredencialesInvalidasException() {
        super("Credenciales inválidas");
    }
}