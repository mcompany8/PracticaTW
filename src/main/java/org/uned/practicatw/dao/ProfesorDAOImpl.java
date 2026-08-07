package org.uned.practicatw.dao;

import org.uned.practicatw.model.Profesor;

public class ProfesorDAOImpl extends GenericDAOImpl<Profesor,Integer> implements ProfesorDAO {
    public ProfesorDAOImpl() {
        super(Profesor.class);
    }
}
