package org.uned.practicatw.service;

import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.Inscripcion;

import java.util.List;

public interface InscripcionService extends GenericService<Inscripcion> {
    List<Estudiante> obtenerEstudiantesPorCurso (Long cursoId);
    List<Curso> obtenerCursosPorEstudiante (Long estudianteId);
    Inscripcion obtenerPorCursoAndEstudiante (Long cursoId, Long estudianteId);
    List<Inscripcion> obtenerPorEstudiante (Long estudianteId);
}
