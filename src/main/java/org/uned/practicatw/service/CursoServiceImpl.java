package org.uned.practicatw.service;

import org.uned.practicatw.dao.CursoDAO;
import org.uned.practicatw.dao.CursoDAOImpl;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Nivel;
import org.uned.practicatw.model.Profesor;

import java.util.List;

public class CursoServiceImpl extends GenericServiceImpl<Curso, CursoDAO> implements CursoService {

    public CursoServiceImpl(CursoDAO cursoDAO) {
        super(cursoDAO);
    }

    @Override
    public List<Curso> obtenerCursosPorProfesor(Long idProfesor) {
        return dao.buscarPorProfesor(idProfesor);
    }

    @Override
    public Curso obtenerCursoPorIdYProfesor(Long id, Long idProfesor) {
        return dao.buscarPorIdAndProfesor(id, idProfesor);
    }

    @Override
    public List<Curso> obtenerCursosRandom(Integer cantidad) {
        return dao.buscarRandom(cantidad);
    }
}
