package org.uned.practicatw.service;

import org.uned.practicatw.model.Usuario;

import java.util.Optional;

public interface AuthService {
    Optional<Usuario> autenticar (String email, String password);
}
