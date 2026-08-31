package org.uned.practicatw.dao;

import org.uned.practicatw.model.Contenido;
import org.uned.practicatw.model.CursoContenido;

import java.util.List;

public interface CursoContenidoDAO extends GenericDAO<CursoContenido> {

    List<CursoContenido> buscarPorCursoAndContenido (Long cursoId, Long contenidoId);
    List<CursoContenido> buscarPorNotCurso (Long cursoId);


}
