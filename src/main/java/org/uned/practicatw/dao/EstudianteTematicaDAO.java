package org.uned.practicatw.dao;

import org.uned.practicatw.model.EstudianteTematica;

import java.util.List;

public interface EstudianteTematicaDAO extends GenericDAO<EstudianteTematica>{
    List<EstudianteTematica> buscarPorEstudiante(Long estudianteId);
}
