package org.uned.practicatw.dao;

import org.uned.practicatw.model.Valoracion;

import java.util.List;

public interface ValoracionDAO extends GenericDAO<Valoracion> {
    Valoracion buscarPorInscripcion(Long inscripcionId);
    List<Valoracion> buscarPorCurso(Long cursoId);
}
