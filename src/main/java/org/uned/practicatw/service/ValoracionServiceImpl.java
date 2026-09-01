package org.uned.practicatw.service;

import org.uned.practicatw.dao.ValoracionDAO;
import org.uned.practicatw.model.Valoracion;

import java.util.List;

public class ValoracionServiceImpl extends GenericServiceImpl<Valoracion, ValoracionDAO> implements ValoracionService {
    public ValoracionServiceImpl(ValoracionDAO dao) {
        super(dao);
    }

    @Override
    public Valoracion obtenerPorInscripcion(Long inscripcionId) {
        return dao.buscarPorInscripcion(inscripcionId);
    }

    @Override
    public List<Valoracion> obtenerPorCurso(Long cursoId) {
        return dao.buscarPorCurso(cursoId);
    }
}
