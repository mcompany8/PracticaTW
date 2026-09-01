// service/exception/FicheroNoPermitidoException.java
package org.uned.practicatw.service.exception;

/** Lanzada por {@link org.uned.practicatw.utils.FilesUtil#copy} cuando el tipo MIME del fichero subido no está entre los permitidos. */
public class FicheroNoPermitidoException extends RuntimeException {
    public FicheroNoPermitidoException(String fichero) {
        super("El fichero " + fichero + " tiene una extensión no permitida.");
    }

    public FicheroNoPermitidoException() {
        super("El fichero tiene una extansión no permitida.");
    }
}