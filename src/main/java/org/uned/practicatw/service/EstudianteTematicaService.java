package org.uned.practicatw.service;

import org.uned.practicatw.model.EstudianteTematica;

import java.util.List;

public interface EstudianteTematicaService extends GenericService<EstudianteTematica> {
    List<EstudianteTematica> obtenerPorEstudiante(Long estudianteId);
}
