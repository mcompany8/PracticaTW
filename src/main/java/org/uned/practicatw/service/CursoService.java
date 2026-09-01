// CursoService.java
package org.uned.practicatw.service;

import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Nivel;
import org.uned.practicatw.model.Profesor;

import java.util.List;

/** Servicio de {@link Curso}. */
public interface CursoService extends GenericService<Curso>{

    /**
     * Cursos de los que es responsable un profesor.
     *
     * @param idProfesor id del profesor
     * @return la lista de cursos
     */
    List<Curso> obtenerCursosPorProfesor (Long idProfesor);

    /**
     * Busca un curso comprobando que pertenece al profesor indicado —
     * comprobación de propiedad (IDOR) antes de dejar ver/editar un curso.
     *
     * @param id         id del curso
     * @param idProfesor id del profesor que debe ser su responsable
     * @return el curso, o {@code null} si no existe o no le pertenece
     */
    Curso obtenerCursoPorIdYProfesor (Long id, Long idProfesor);

    /**
     * Selección aleatoria de cursos para la portada.
     *
     * @param cantidad número máximo de cursos a devolver
     * @return la lista de cursos aleatorios
     */
    List<Curso> obtenerCursosRandom (Integer cantidad);

    /**
     * Todos los cursos, con sus temáticas ya cargadas.
     *
     * @return la lista completa de cursos
     */
    List<Curso> obtenerTodosConTematicas();

    /**
     * Cursos que tienen asignada una temática concreta.
     *
     * @param tematicaId id de la temática
     * @return la lista de cursos con esa temática
     */
    List<Curso> obtenerCursosPorTematica(Long tematicaId);
}