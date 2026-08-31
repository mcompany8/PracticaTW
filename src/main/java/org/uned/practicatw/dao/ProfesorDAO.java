package org.uned.practicatw.dao;

import org.uned.practicatw.model.Profesor;

import java.util.List;

public interface ProfesorDAO extends GenericDAO<Profesor> {

    public List<Profesor> buscarProfesores();

}
