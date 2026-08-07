package org.uned.practicatw.dao;

import org.uned.practicatw.model.Curso;

public class CursoDAOImpl extends GenericDAOImpl<Curso,Integer> implements CursoDAO {
    public CursoDAOImpl() {
        super(Curso.class);
    }
}
