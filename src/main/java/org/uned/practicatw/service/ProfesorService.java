package org.uned.practicatw.service;

import org.uned.practicatw.dao.GenericDAO;
import org.uned.practicatw.model.Profesor;

import java.util.List;

/** Servicio de {@link Profesor}. */
public interface ProfesorService extends GenericService<Profesor> {

    /**
     * Todos los profesores (incluye administradores, por polimorfismo de herencia).
     *
     * @return la lista completa de profesores
     */
    List<Profesor> obtenerProfesores();
}