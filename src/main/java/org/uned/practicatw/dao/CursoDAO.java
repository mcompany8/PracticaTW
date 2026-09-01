package org.uned.practicatw.dao;

import org.uned.practicatw.model.Curso;

import java.util.List;

/** DAO de {@link Curso}. */
public interface CursoDAO extends GenericDAO<Curso> {

    /**
     * Cursos de los que es responsable un profesor.
     *
     * @param idProfesor id del profesor
     * @return la lista de cursos (vacía si no tiene ninguno)
     */
    List<Curso> buscarPorProfesor(Long idProfesor);

    /**
     * Busca un curso por id, comprobando además que pertenece al profesor
     * indicado — pensado para las comprobaciones de propiedad (IDOR) antes
     * de dejar a un profesor ver o modificar un curso.
     *
     * @param id         id del curso
     * @param idProfesor id del profesor que debe ser su responsable
     * @return el curso, o {@code null} si no existe o no pertenece a ese profesor
     */
    Curso buscarPorIdAndProfesor(Long id, Long idProfesor);

    /**
     * Selección aleatoria de cursos, con sus temáticas ya cargadas, para la
     * sección de "cursos destacados" de la portada.
     *
     * @param cantidad número máximo de cursos a devolver
     * @return la lista de cursos aleatorios
     */
    List<Curso> buscarRandom(Integer cantidad);

    /**
     * Todos los cursos, con sus temáticas ya cargadas (evita N+1 al
     * renderizar el catálogo completo).
     *
     * @return la lista completa de cursos
     */
    List<Curso> buscarTodosConTematicas();

    /**
     * Cursos que tienen asignada una temática concreta.
     *
     * @param tematicaId id de la temática
     * @return la lista de cursos con esa temática
     */
    List<Curso> buscarPorTematica(Long tematicaId);
}