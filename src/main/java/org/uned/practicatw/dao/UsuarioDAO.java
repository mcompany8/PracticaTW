package org.uned.practicatw.dao;

import org.uned.practicatw.model.Usuario;

import java.util.List;
import java.util.Optional;

/** DAO de {@link Usuario}. */
public interface UsuarioDAO extends GenericDAO<Usuario> {

    /**
     * Busca un usuario por su email, usado como identificador de acceso.
     *
     * @param email el email a buscar
     * @return el usuario encontrado, o {@code Optional.empty()} si no existe ninguno con ese email
     */
    Optional<Usuario> buscarPorEmail(String email);

    /**
     * Cambia el rol de un usuario ya existente, escribiendo directamente
     * sobre la columna discriminadora {@code tipo_usuario} mediante una
     * consulta nativa. JPA no permite cambiar el tipo concreto de una
     * entidad con herencia {@code SINGLE_TABLE} reasignando el objeto Java, así
     * que este es el único mecanismo posible — y solo es seguro usarlo entre
     * {@link org.uned.practicatw.model.Profesor} y
     * {@link org.uned.practicatw.model.Administrador} (ver la nota de
     * {@link org.uned.practicatw.model.Usuario} sobre por qué no vale para
     * {@link org.uned.practicatw.model.Estudiante}).
     *
     * @param id   id del usuario
     * @param tipo nuevo valor de la columna discriminadora ({@code "PROFESOR"} o {@code "ADMINISTRADOR"})
     */
    void cambiarTipo(Long id, String tipo);


}