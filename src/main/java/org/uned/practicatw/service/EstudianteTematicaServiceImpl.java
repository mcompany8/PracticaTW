package org.uned.practicatw.service;

import org.uned.practicatw.dao.EstudianteTematicaDAO;
import org.uned.practicatw.model.EstudianteTematica;

import java.util.List;

public class EstudianteTematicaServiceImpl extends GenericServiceImpl<EstudianteTematica, EstudianteTematicaDAO> implements EstudianteTematicaService {
    public EstudianteTematicaServiceImpl(EstudianteTematicaDAO dao) {
        super(dao);
    }

    @Override
    public List<EstudianteTematica> obtenerPorEstudiante(Long estudianteId) {
        return dao.buscarPorEstudiante(estudianteId);
    }
}
