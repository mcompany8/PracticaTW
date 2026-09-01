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

    @Override
    public List<Contenido> obtenerPorCurso(Long cursoId) {
        return dao.buscarPorCurso(cursoId);
    }

    @Override
    public void actualizarOrden(Long cursoId, Long materialId, Integer ordenViejo, Integer ordenNuevo) {
        dao.actualizarOrden(cursoId, materialId, ordenViejo, ordenNuevo);
    }

    @Override
    public void hacerHueco(Long cursoId, Integer orden) {
        dao.hacerHueco(cursoId, orden);
    }

    @Override
    public void cerrarHueco(Long cursoId, Integer ordenEliminado) {
        dao.cerrarHueco(cursoId, ordenEliminado);
    }
}
