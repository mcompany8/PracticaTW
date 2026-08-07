package org.uned.practicatw.dao;

import org.uned.practicatw.model.Estudiante;

public class EstudianteDAOImpl extends GenericDAOImpl<Estudiante,Integer> implements EstudianteDAO {
    public EstudianteDAOImpl() {
        super(Estudiante.class);
    }
}
