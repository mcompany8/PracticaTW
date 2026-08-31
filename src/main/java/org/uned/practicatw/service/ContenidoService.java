package org.uned.practicatw.service;

import org.uned.practicatw.model.Contenido;

import java.util.List;

public interface ContenidoService extends GenericService<Contenido> {
    List<Contenido> obtenerPorPropietarioOrPublico(Long idPropietario);
}
