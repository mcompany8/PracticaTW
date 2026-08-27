package org.uned.practicatw.dao;

import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.model.Administrador;

public class AdministradorDAOImpl extends GenericDAOImpl<Administrador> implements AdministradorDAO {
    public AdministradorDAOImpl(EntityManagerFactory emf) {
        super(Administrador.class, emf);
    }
}
