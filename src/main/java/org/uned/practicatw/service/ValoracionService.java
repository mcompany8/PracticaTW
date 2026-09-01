package org.uned.practicatw.service;

import org.uned.practicatw.model.Valoracion;

import java.util.List;

/** Servicio de {@link Valoracion}. */
public interface ValoracionService extends GenericService<Valoracion> {

    /**
     * Busca la valoración de una inscripción.
     *
     * @param inscripcionId id de la inscripción
     * @return la valoración, o {@code null} si no existe
     */
    Valoracion obtenerPorInscripcion(Long inscripcionId);

    /**
     * Valoraciones de un curso, para el panel de estadísticas.
     *
     * @param cursoId id del curso
     * @return la lista de valoraciones
     */
    List<Valoracion> obtenerPorCurso(Long cursoId);
}