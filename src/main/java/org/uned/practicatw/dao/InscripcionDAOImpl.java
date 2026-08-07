package org.uned.practicatw.dao;

import org.uned.practicatw.model.Inscripcion;

public class InscripcionDAOImpl extends GenericDAOImpl<Inscripcion,Long> implements InscripcionDAO {
    public InscripcionDAOImpl() {
        super(Inscripcion.class);
    }
}
