package org.uned.practicatw.service;

import org.uned.practicatw.model.Valoracion;

import java.util.List;

public interface ValoracionService extends GenericService<Valoracion> {
    Valoracion obtenerPorInscripcion(Long inscripcionId);
    List<Valoracion> obtenerPorCurso(Long cursoId);
}
