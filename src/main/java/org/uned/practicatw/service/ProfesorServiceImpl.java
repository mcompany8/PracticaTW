package org.uned.practicatw.service;

import org.uned.practicatw.dao.ProfesorDAO;
import org.uned.practicatw.model.Profesor;

import java.util.List;

public class ProfesorServiceImpl extends GenericServiceImpl<Profesor, ProfesorDAO> implements ProfesorService {

    public ProfesorServiceImpl(ProfesorDAO dao) {
        super(dao);
    }

    public List<Profesor> obtenerProfesores() {
        return dao.buscarProfesores();
    }
}
