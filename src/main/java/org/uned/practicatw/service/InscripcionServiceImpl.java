package org.uned.practicatw.service;

import org.uned.practicatw.dao.InscripcionDAO;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.Inscripcion;

import java.util.List;

public class InscripcionServiceImpl extends GenericServiceImpl<Inscripcion, InscripcionDAO> implements InscripcionService {

    public InscripcionServiceImpl(InscripcionDAO dao) {
        super(dao);
    }

    @Override
    public List<Estudiante> obtenerEstudiantesPorCurso(Long cursoId) {
        return dao.buscarEstudiantesPorCurso(cursoId);
    }

    @Override
    public List<Curso> obtenerCursosPorEstudiante(Long estudianteId) {
        return dao.buscarCursosPorEstudiante(estudianteId);
    }

    @Override
    public Inscripcion obtenerPorCursoAndEstudiante(Long cursoId, Long EstudianteId) {
        return dao.buscarPorCursoAndEstudiante(cursoId, EstudianteId);
    }

    @Override
    public List<Inscripcion> obtenerPorEstudiante(Long estudianteId) {
        return dao.buscarPorEstudiante(estudianteId);
    }
}
