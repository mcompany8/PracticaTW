package org.uned.practicatw.service;

import org.uned.practicatw.model.Contenido;

import java.util.List;

public interface ContenidoService extends GenericService<Contenido> {
    List<Contenido> obtenerPorCurso(Long cursoId);
    void actualizarOrden(Long cursoId, Long materialId, Integer ordenViejo, Integer ordenNuevo);
    void hacerHueco(Long cursoId, Integer orden);
    void cerrarHueco(Long cursoId, Integer ordenEliminado);
}