package org.uned.practicatw.dao;

import org.uned.practicatw.model.Curso;

import java.util.List;

public interface CursoDAO extends GenericDAO<Curso> {
    List<Curso> buscarPorProfesor(Long idProfesor);
    Curso buscarPorIdAndProfesor(Long id, Long idProfesor);
    List<Curso> buscarRandom(Integer cantidad);
    List<Curso> buscarTodosConTematicas();
    List<Curso> buscarPorTematica(Long tematicaId);
}
