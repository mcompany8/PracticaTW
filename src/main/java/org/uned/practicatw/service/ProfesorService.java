package org.uned.practicatw.service;

import org.uned.practicatw.dao.GenericDAO;
import org.uned.practicatw.model.Profesor;

import java.util.List;

public interface ProfesorService extends GenericService<Profesor> {

    List<Profesor> obtenerProfesores();
}
