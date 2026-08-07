package org.uned.practicatw.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T, ID> {
    T guardar (T entidad);
    Optional<T> buscarPorId (ID id);
    List<T> buscarTodos();
    void actualizar (T entidad);
    void eliminar (ID id);
}