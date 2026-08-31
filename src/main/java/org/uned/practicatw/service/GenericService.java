package org.uned.practicatw.service;

import java.util.List;
import java.util.Optional;

interface GenericService<T> {
    T crear(T t);
    void actualizar(T t);
    void eliminar(Long id);
    Optional<T> obtenerPorId(Long id);
    List<T> obtenerTodos();
}
