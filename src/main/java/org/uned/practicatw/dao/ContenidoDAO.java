package org.uned.practicatw.dao;

import org.uned.practicatw.model.Contenido;

import java.util.List;

/**
 * DAO de {@link Contenido} (materiales de curso), con las operaciones
 * necesarias para mantener consistente el campo {@code orden} frente a la
 * unique constraint {@code (curso_id, orden)} al insertar, reordenar o
 * eliminar un material.
 */
public interface ContenidoDAO extends GenericDAO<Contenido> {

    /**
     * Materiales de un curso, ordenados por {@code orden} ascendente.
     *
     * @param cursoId id del curso
     * @return la lista de materiales (vacía si el curso no tiene ninguno)
     */
    List<Contenido> buscarPorCurso(Long cursoId);

    /**
     * Mueve un material de {@code ordenViejo} a {@code ordenNuevo}, desplazando
     * en cascada el resto de materiales del curso que quedan entre medias.
     * Usa una posición sentinela intermedia fuera de rango para no violar la
     * unique constraint mientras se reordena.
     *
     * @param cursoId    id del curso al que pertenece el material
     * @param materialId id del material a mover
     * @param ordenViejo posición actual del material
     * @param ordenNuevo posición destino
     */
    void actualizarOrden(Long cursoId, Long materialId, Integer ordenViejo, Integer ordenNuevo);

    /**
     * Desplaza una posición hacia atrás todos los materiales del curso con
     * {@code orden >= orden}, dejando ese hueco libre para insertar un
     * material nuevo ahí sin colisionar con la unique constraint.
     *
     * @param cursoId id del curso
     * @param orden   posición a partir de la cual desplazar (inclusive)
     */
    void hacerHueco(Long cursoId, Integer orden);

    /**
     * Desplaza una posición hacia delante todos los materiales del curso con
     * {@code orden > ordenEliminado}, cerrando el hueco que deja un material
     * recién eliminado.
     *
     * @param cursoId        id del curso
     * @param ordenEliminado posición que ha quedado libre
     */
    void cerrarHueco(Long cursoId, Integer ordenEliminado);

}