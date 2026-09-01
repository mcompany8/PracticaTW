package org.uned.practicatw.dao;

import java.util.List;
import java.util.Optional;

/**
 * Operaciones CRUD comunes a todos los DAO de la aplicación.
 * <p>
 * Cada DAO concreto (p. ej. {@link CursoDAO}, {@link UsuarioDAO}) extiende esta
 * interfaz añadiendo las consultas específicas de su entidad (normalmente
 * respaldadas por {@code @NamedQuery} definidas en el propio modelo), y su
 * implementación hereda de {@link GenericDAOImpl}, que ya resuelve estas
 * operaciones básicas contra JPA/Hibernate.
 * <p>
 * Cada método abre y cierra su propio {@code EntityManager}: no hay una
 * transacción ni una sesión de Hibernate compartida entre llamadas, así que
 * cualquier colección o relación {@code LAZY} de la entidad devuelta queda
 * sin inicializar en cuanto el método retorna (ver notas de cada DAO concreto
 * sobre qué relaciones se cargan mediante {@code JOIN FETCH}).
 *
 * @param <T> el tipo de entidad gestionada por el DAO
 */
public interface GenericDAO<T> {

    /**
     * Persiste una entidad nueva (equivalente a {@code EntityManager.persist}).
     *
     * @param entidad la entidad a guardar; tras la llamada, su id ya está
     *                asignado (estrategia {@code IDENTITY})
     * @return la misma entidad, con el id generado
     */
    T guardar(T entidad);

    /**
     * Busca una entidad por su clave primaria.
     *
     * @param id el id de la entidad
     * @return la entidad encontrada, o {@code Optional.empty()} si no existe
     *         ninguna con ese id
     */
    Optional<T> buscarPorId(Long id);

    /**
     * Devuelve todas las filas de la tabla asociada a esta entidad, sin
     * ningún filtro ni orden garantizado.
     *
     * @return la lista completa de entidades (vacía si no hay ninguna)
     */
    List<T> buscarTodos();

    /**
     * Actualiza una entidad ya existente (equivalente a {@code EntityManager.merge}).
     *
     * @param entidad la entidad con los cambios a persistir; debe tener el id
     *                de una fila ya existente
     */
    void actualizar(T entidad);

    /**
     * Elimina la entidad con el id indicado.
     *
     * @param id el id de la entidad a eliminar
     */
    void eliminar(Long id);
}