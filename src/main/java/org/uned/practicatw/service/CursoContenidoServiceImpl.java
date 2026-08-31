package org.uned.practicatw.service;

import org.uned.practicatw.dao.CursoContenidoDAO;
import org.uned.practicatw.dao.GenericDAO;
import org.uned.practicatw.model.CursoContenido;

import java.util.List;

public class CursoContenidoServiceImpl extends GenericServiceImpl<CursoContenido, CursoContenidoDAO> implements CursoContenidoService {

    public CursoContenidoServiceImpl(CursoContenidoDAO dao) {
        super(dao);
    }

    @Override
    public List<CursoContenido> buscarPorCursoAndContenido(Long cursoId, Long contenidoId) {
        return dao.buscarPorCursoAndContenido(cursoId, contenidoId);
    }

    @Override
    public List<CursoContenido> buscarPorNotCurso(Long cursoId) {
        return dao.buscarPorNotCurso(cursoId);
    }
}
