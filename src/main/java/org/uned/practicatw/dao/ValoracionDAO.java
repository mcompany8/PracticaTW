package org.uned.practicatw.dao;

import org.uned.practicatw.model.Valoracion;

import java.util.List;

/** DAO de {@link Valoracion}. */
public interface ValoracionDAO extends GenericDAO<Valoracion> {

    /**
     * Busca la valoración asociada a una inscripción concreta.
     *
     * @param inscripcionId id de la inscripción
     * @return la valoración, o {@code null} si el alumno todavía no ha valorado ese curso
     */
    Valoracion buscarPorInscripcion(Long inscripcionId);

    /**
     * Valoraciones de un curso, con la inscripción y el estudiante ya
     * cargados, para el panel de estadísticas del profesor.
     *
     * @param cursoId id del curso
     * @return la lista de valoraciones del curso (vacía si no tiene ninguna)
     */
    List<Valoracion> buscarPorCurso(Long cursoId);
}