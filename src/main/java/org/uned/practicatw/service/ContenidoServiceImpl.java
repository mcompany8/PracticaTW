package org.uned.practicatw.service;

import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.dao.ContenidoDAO;
import org.uned.practicatw.dao.GenericDAOImpl;
import org.uned.practicatw.model.Contenido;

import java.util.List;
import java.util.Optional;

public class ContenidoServiceImpl extends GenericServiceImpl<Contenido, ContenidoDAO> implements ContenidoService {

    public ContenidoServiceImpl(ContenidoDAO dao) {
        super(dao);
    }
}
