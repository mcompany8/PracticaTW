package org.uned.practicatw.service;

import org.uned.practicatw.model.Usuario;

import java.util.Optional;

/**
 * Servicio de autenticación. A diferencia del resto de servicios, no extiende
 * {@link GenericService} — no gestiona el ciclo de vida CRUD de ninguna
 * entidad, solo la comprobación de credenciales en el login.
 */
public interface AuthService {

    /**
     * Comprueba las credenciales de acceso de un usuario.
     *
     * @param email    el email introducido
     * @param password la contraseña en claro introducida (se compara contra
     *                 el hash BCrypt almacenado, nunca en texto plano)
     * @return el usuario autenticado, o {@code Optional.empty()} si el email
     *         no existe o la contraseña no coincide
     */
    Optional<Usuario> autenticar (String email, String password);
}