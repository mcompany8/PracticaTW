package org.uned.practicatw.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
    /**
     * @param entidad
     * @return
     */
    T guardar (T entidad);
    Optional<T> buscarPorId (Long id);
    List<T> buscarTodos();
    void actualizar (T entidad);
    void eliminar (Long id);
}