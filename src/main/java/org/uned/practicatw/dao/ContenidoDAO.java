package org.uned.practicatw.dao;

import org.uned.practicatw.model.Contenido;

import java.util.List;

public interface ContenidoDAO extends GenericDAO<Contenido> {
    List<Contenido> buscarPorCurso(Long cursoId);
    void actualizarOrden(Long cursoId, Long materialId, Integer ordenViejo, Integer ordenNuevo);
    void hacerHueco(Long cursoId, Integer orden);
    void cerrarHueco(Long cursoId, Integer ordenEliminado);

}
