package org.uned.practicatw.service;

import org.uned.practicatw.dao.GenericDAO;
import org.uned.practicatw.dao.GenericDAOImpl;

import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl<T, D extends GenericDAO<T>> implements GenericService<T> {

    protected final D dao;

    public GenericServiceImpl(D dao) {
        this.dao = dao;
    }

    public T crear(T t) {
        return dao.guardar(t);
    }

    public void actualizar(T t) {
        dao.actualizar(t);
    }

    public void eliminar(Long id) {
        dao.eliminar(id);
    }

    public Optional<T> obtenerPorId(Long id) {
        return dao.buscarPorId(id);
    }

    public List<T> listar() {
        return dao.buscarTodos();
    }
}
