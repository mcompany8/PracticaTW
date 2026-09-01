package org.uned.practicatw.service;

import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.Inscripcion;

import java.util.List;

/** Servicio de {@link Inscripcion}. */
public interface InscripcionService extends GenericService<Inscripcion> {

    /**
     * Estudiantes matriculados en un curso.
     *
     * @param cursoId id del curso
     * @return la lista de estudiantes
     */
    List<Estudiante> obtenerEstudiantesPorCurso (Long cursoId);

    /**
     * Cursos en los que está matriculado un estudiante.
     *
     * @param estudianteId id del estudiante
     * @return la lista de cursos
     */
    List<Curso> obtenerCursosPorEstudiante (Long estudianteId);

    /**
     * Busca la inscripción de un estudiante en un curso, con su valoración
     * ya cargada si existe.
     *
     * @param cursoId      id del curso
     * @param estudianteId id del estudiante
     * @return la inscripción, o {@code null} si no está matriculado
     */
    Inscripcion obtenerPorCursoAndEstudiante (Long cursoId, Long estudianteId);

    /**
     * Inscripciones de un estudiante, con el curso cargado.
     *
     * @param estudianteId id del estudiante
     * @return la lista de inscripciones
     */
    List<Inscripcion> obtenerPorEstudiante (Long estudianteId);

    /**
     * Inscripciones de un curso, con el estudiante cargado, para el listado
     * de matriculados del profesor.
     *
     * @param cursoId id del curso
     * @return la lista de inscripciones
     */
    List<Inscripcion> obtenerPorCurso(Long cursoId);
}