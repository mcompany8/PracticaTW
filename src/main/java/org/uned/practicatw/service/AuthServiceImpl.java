package org.uned.practicatw.service;

import org.uned.practicatw.dao.UsuarioDAO;
import org.uned.practicatw.dao.UsuarioDAOImpl;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.utils.PasswordUtil;

import java.util.Optional;

public class AuthServiceImpl implements AuthService {

    private final UsuarioDAO usuarioDAO;

    public AuthServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public Optional<Usuario> autenticar(String email, String password) {

        Optional<Usuario> usuario = usuarioDAO.buscarPorEmail(email);

        if (usuario.isPresent() && PasswordUtil.checkPassword(password, usuario.get().getPassword())) {
            return usuario;
        } else {
            return Optional.empty();
        }
    }
}
