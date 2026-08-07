package org.uned.practicatw.dao;

import org.uned.practicatw.model.Usuario;

public class UsuarioDAOImpl extends GenericDAOImpl<Usuario, Integer> implements UsuarioDAO {
    protected UsuarioDAOImpl() {
        super(Usuario.class);
    }
}
