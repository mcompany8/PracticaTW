package org.uned.practicatw.service;

import org.uned.practicatw.dao.GenericDAO;
import org.uned.practicatw.model.CursoContenido;

import java.util.List;

public interface CursoContenidoService extends GenericService<CursoContenido> {
    List<CursoContenido> buscarPorCursoAndContenido (Long cursoId, Long contenidoId);
    List<CursoContenido> buscarPorNotCurso (Long cursoId);
}
