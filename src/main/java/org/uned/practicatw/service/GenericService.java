package org.uned.practicatw.service;

import java.util.List;
import java.util.Optional;

/**
 * Contrato común a todos los servicios de negocio de la aplicación.
 * <p>
 * Actúa como una fina capa sobre el {@code DAO} correspondiente: en la
 * mayoría de los casos delega directamente en él, pero es aquí (no en el DAO)
 * donde deben vivir las reglas de negocio y validaciones que no son puramente
 * de acceso a datos — por ejemplo, {@link UsuarioService} comprueba que el
 * email no esté ya registrado antes de crear un usuario.
 *
 * @param <T> el tipo de entidad gestionada por el servicio
 */
public interface GenericService<T> {

    /**
     * Crea una nueva entidad, aplicando primero cualquier validación de
     * negocio propia de la entidad (ver la implementación concreta).
     *
     * @param t la entidad a crear
     * @return la entidad creada, con su id ya asignado
     */
    T crear(T t);

    /**
     * Actualiza una entidad ya existente.
     *
     * @param t la entidad con los cambios a persistir
     */
    void actualizar(T t);

    /**
     * Elimina la entidad con el id indicado.
     *
     * @param id el id de la entidad a eliminar
     */
    void eliminar(Long id);

    /**
     * Busca una entidad por su id.
     *
     * @param id el id de la entidad
     * @return la entidad encontrada, o {@code Optional.empty()} si no existe
     */
    Optional<T> obtenerPorId(Long id);

    /**
     * Devuelve todas las entidades de este tipo.
     *
     * @return la lista completa de entidades (vacía si no hay ninguna)
     */
    List<T> obtenerTodos();
}