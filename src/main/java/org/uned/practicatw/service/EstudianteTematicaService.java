package org.uned.practicatw.service;

import org.uned.practicatw.model.EstudianteTematica;

import java.util.List;

/** Servicio de {@link EstudianteTematica} (temáticas de interés). */
public interface EstudianteTematicaService extends GenericService<EstudianteTematica> {

    /**
     * Temáticas de interés de un estudiante.
     *
     * @param estudianteId id del estudiante
     * @return la lista de asociaciones estudiante-temática
     */
    List<EstudianteTematica> obtenerPorEstudiante(Long estudianteId);
}