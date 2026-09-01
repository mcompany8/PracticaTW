package org.uned.practicatw.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utilidad de hash de contraseñas con BCrypt. Es el único sitio de la
 * aplicación donde se debe manejar una contraseña en texto plano (para
 * convertirla en hash antes de guardarla, o para compararla contra el hash
 * ya almacenado) — en cualquier otro punto del código, una contraseña
 * siempre debería estar ya en forma de hash.
 *
 * @implNote {@code WORKLOAD} está declarado pero no se usa: {@link BCrypt#gensalt()}
 * se llama sin argumentos, así que el coste (rondas) de BCrypt es siempre el
 * valor por defecto de la librería, no el que sugiere esta constante.
 */
public class PasswordUtil {

    private static final int WORKLOAD = 12;

    private PasswordUtil () {}

    /**
     * Genera el hash BCrypt de una contraseña en texto plano.
     *
     * @param passwordPlano la contraseña sin cifrar
     * @return el hash BCrypt, listo para guardar en {@code Usuario.password}
     */
    public static String hashPassword(String passwordPlano) {
        return BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
    }

    /**
     * Comprueba si una contraseña en texto plano corresponde a un hash BCrypt dado.
     *
     * @param passwordPlano la contraseña introducida por el usuario
     * @param passwordHash  el hash almacenado en {@code Usuario.password}
     * @return {@code true} si coinciden
     */
    public static boolean checkPassword(String passwordPlano, String passwordHash) {
        return BCrypt.checkpw(passwordPlano, passwordHash);
    }
}