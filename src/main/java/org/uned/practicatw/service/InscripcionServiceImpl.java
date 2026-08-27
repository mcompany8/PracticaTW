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

    public List<Estudiante> obtenerEstudiantesPorCurso(Long cursoId) {
        return dao.buscarEstudiantesPorCurso(cursoId);
    }

    public List<Curso> obtenerCursosPorEstudiante(Long estudianteId) {
        return dao.buscarCursosPorEstudiante(estudianteId);
    }
}
