package org.uned.practicatw.dao;

import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.model.Contenido;

import java.util.List;
import java.util.Optional;

public class ContenidoDAOImpl extends GenericDAOImpl<Contenido> implements ContenidoDAO {

    public ContenidoDAOImpl(EntityManagerFactory emf) {
        super(Contenido.class, emf);
    }

}
