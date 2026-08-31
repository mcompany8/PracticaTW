package org.uned.practicatw.dao;

import org.uned.practicatw.model.Contenido;

import java.util.List;

public interface ContenidoDAO extends GenericDAO<Contenido> {
    List<Contenido> buscarPorPropietarioOrPublico (Long propietario);
}
