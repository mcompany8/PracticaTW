package org.uned.practicatw.service;

import org.uned.practicatw.model.Usuario;

import java.util.List;

/** Servicio de {@link Usuario}. */
public interface UsuarioService extends GenericService<Usuario> {

    /**
     * Cambia el rol de un usuario (solo seguro entre Profesor y Administrador,
     * ver {@link org.uned.practicatw.dao.UsuarioDAO#cambiarTipo}).
     *
     * @param id   id del usuario
     * @param tipo nuevo valor de la columna discriminadora ({@code "PROFESOR"} o {@code "ADMINISTRADOR"})
     */
    void cambiarTipo(Long id, String tipo);
}