package org.uned.practicatw.dao;

import org.uned.practicatw.model.Profesor;

import java.util.List;

/** DAO de {@link Profesor}. */
public interface ProfesorDAO extends GenericDAO<Profesor> {

    /**
     * Todos los profesores (incluye a los {@link org.uned.practicatw.model.Administrador},
     * por polimorfismo de la herencia {@code SINGLE_TABLE} de {@link org.uned.practicatw.model.Usuario}).
     *
     * @return la lista completa de profesores
     */
    public List<Profesor> buscarProfesores();

}