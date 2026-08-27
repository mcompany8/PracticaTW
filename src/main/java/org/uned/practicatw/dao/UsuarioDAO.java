package org.uned.practicatw.dao;

import org.uned.practicatw.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioDAO extends GenericDAO<Usuario> {

    Optional<Usuario> buscarPorEmail(String email);


}
