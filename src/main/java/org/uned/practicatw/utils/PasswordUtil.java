package org.uned.practicatw.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    private static final int WORKLOAD = 12;

    private PasswordUtil () {}

    public static String hashPassword(String passwordPlano) {
        return BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
    }

    public static boolean checkPassword(String passwordPlano, String passwordHash) {
        return BCrypt.checkpw(passwordPlano, passwordHash);
    }
}
