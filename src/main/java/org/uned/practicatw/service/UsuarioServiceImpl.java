package org.uned.practicatw.service;

import org.uned.practicatw.dao.GenericDAO;
import org.uned.practicatw.dao.UsuarioDAO;
import org.uned.practicatw.dao.UsuarioDAOImpl;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.exception.EmailYaRegistradoException;

import java.util.List;


public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, UsuarioDAO> implements UsuarioService {

    public UsuarioServiceImpl(UsuarioDAO dao) {
        super(dao);
    }

    @Override
    public Usuario crear(Usuario usuario) {

        String email = usuario.getEmail();

        dao.buscarPorEmail(email)
                .ifPresentOrElse(
                        u -> {
                            throw new EmailYaRegistradoException(email);
                        },
                        () -> {}
                );

        return dao.guardar(usuario);

    }

}
