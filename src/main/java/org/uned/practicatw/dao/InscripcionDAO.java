package org.uned.practicatw.dao;

import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.Inscripcion;

import java.util.List;

/** DAO de {@link Inscripcion}. */
public interface InscripcionDAO extends GenericDAO<Inscripcion> {

    /**
     * Estudiantes matriculados en un curso.
     *
     * @param cursoId id del curso
     * @return la lista de estudiantes matriculados
     */
    public List<Estudiante> buscarEstudiantesPorCurso(Long cursoId);

    /**
     * Cursos en los que está matriculado un estudiante.
     *
     * @param estudianteId id del estudiante
     * @return la lista de cursos
     */
    public List<Curso> buscarCursosPorEstudiante(Long estudianteId);

    /**
     * Busca la inscripción de un estudiante concreto en un curso concreto,
     * con su {@link org.uned.practicatw.model.Valoracion} ya cargada si existe.
     *
     * @param cursoId      id del curso
     * @param estudianteId id del estudiante
     * @return la inscripción, o {@code null} si el estudiante no está matriculado en ese curso
     */
    public Inscripcion buscarPorCursoAndEstudiante(Long cursoId, Long estudianteId);

    /**
     * Inscripciones de un estudiante, con el curso ya cargado, ordenadas de
     * más reciente a más antigua.
     *
     * @param estudianteId id del estudiante
     * @return la lista de inscripciones del estudiante
     */
    public List<Inscripcion> buscarPorEstudiante (Long estudianteId);

    /**
     * Inscripciones de un curso, con el estudiante ya cargado, ordenadas de
     * más reciente a más antigua.
     *
     * @param cursoId id del curso
     * @return la lista de inscripciones del curso
     */
    public List<Inscripcion> buscarPorCurso (Long cursoId);



}