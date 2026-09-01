package org.uned.practicatw.dao;

import org.uned.practicatw.model.EstudianteTematica;

import java.util.List;

/** DAO de {@link EstudianteTematica} (temáticas de interés de un estudiante). */
public interface EstudianteTematicaDAO extends GenericDAO<EstudianteTematica>{

    /**
     * Temáticas de interés de un estudiante, con la {@link org.uned.practicatw.model.Tematica}
     * ya cargada.
     *
     * @param estudianteId id del estudiante
     * @return la lista de asociaciones estudiante-temática (vacía si no ha marcado ninguna)
     */
    List<EstudianteTematica> buscarPorEstudiante(Long estudianteId);
}