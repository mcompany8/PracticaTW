package org.uned.practicatw.service;

import org.uned.practicatw.dao.EstudianteDAO;
import org.uned.practicatw.model.Estudiante;

public class EstudianteServiceImpl extends GenericServiceImpl<Estudiante, EstudianteDAO> implements EstudianteService {
    public EstudianteServiceImpl(EstudianteDAO dao) {
        super(dao);
    }
}
