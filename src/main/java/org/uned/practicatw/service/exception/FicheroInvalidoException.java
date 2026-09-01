// service/exception/FicheroInvalidoException.java
package org.uned.practicatw.service.exception;

/** Lanzada por {@link org.uned.practicatw.utils.FilesUtil#copy} cuando el {@code Part} recibido está vacío (tamaño 0). */
public class FicheroInvalidoException extends RuntimeException {
    public FicheroInvalidoException(String fileName) {
        super("El fichero " + fileName + " está vacío o no existe.");
    }

    public FicheroInvalidoException() {
        super("El fichero está vacío o no existe.");
    }
}