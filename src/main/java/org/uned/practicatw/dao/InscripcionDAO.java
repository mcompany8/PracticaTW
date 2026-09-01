package org.uned.practicatw.dao;

import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.Inscripcion;

import java.util.List;

public interface InscripcionDAO extends GenericDAO<Inscripcion> {

    public List<Estudiante> buscarEstudiantesPorCurso(Long cursoId);
    public List<Curso> buscarCursosPorEstudiante(Long estudianteId);
    public Inscripcion buscarPorCursoAndEstudiante(Long cursoId, Long estudianteId);
    public List<Inscripcion> buscarPorEstudiante (Long estudianteId);
    public List<Inscripcion> buscarPorCurso (Long cursoId);



}
