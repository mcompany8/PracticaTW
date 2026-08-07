package org.uned.practicatw.dao;

import org.uned.practicatw.model.Administrador;

public class AdministradorDAOImpl extends GenericDAOImpl<Administrador,Integer> implements AdministradorDAO {
    public AdministradorDAOImpl() {
        super(Administrador.class);
    }
}
