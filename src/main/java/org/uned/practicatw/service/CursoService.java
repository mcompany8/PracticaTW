package org.uned.practicatw.service;

import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Nivel;
import org.uned.practicatw.model.Profesor;

import java.util.List;

public interface CursoService extends GenericService<Curso>{

    List<Curso> obtenerCursosPorProfesor (Long idProfesor);
    Curso obtenerCursoPorIdYProfesor (Long id, Long idProfesor);
    List<Curso> obtenerCursosRandom (Integer cantidad);
    List<Curso> obtenerTodosConTematicas();
    List<Curso> obtenerCursosPorTematica(Long tematicaId);
}
