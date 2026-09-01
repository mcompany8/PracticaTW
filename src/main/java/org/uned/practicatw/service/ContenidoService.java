package org.uned.practicatw.service;

import org.uned.practicatw.model.Contenido;

import java.util.List;

/** Servicio de {@link Contenido} (materiales de curso). Ver {@link org.uned.practicatw.dao.ContenidoDAO}
 *  para el detalle de la lógica de reordenamiento, que este servicio se limita a delegar. */
public interface ContenidoService extends GenericService<Contenido> {

    /**
     * Materiales de un curso, ordenados por posición.
     *
     * @param cursoId id del curso
     * @return la lista de materiales
     */
    List<Contenido> obtenerPorCurso(Long cursoId);

    /**
     * Mueve un material a una nueva posición, desplazando al resto.
     *
     * @param cursoId    id del curso
     * @param materialId id del material a mover
     * @param ordenViejo posición actual
     * @param ordenNuevo posición destino
     */
    void actualizarOrden(Long cursoId, Long materialId, Integer ordenViejo, Integer ordenNuevo);

    /**
     * Libera una posición desplazando hacia atrás al resto de materiales,
     * antes de insertar uno nuevo ahí.
     *
     * @param cursoId id del curso
     * @param orden   posición a liberar
     */
    void hacerHueco(Long cursoId, Integer orden);

    /**
     * Cierra el hueco que deja un material recién eliminado, desplazando
     * hacia delante al resto.
     *
     * @param cursoId        id del curso
     * @param ordenEliminado posición que ha quedado libre
     */
    void cerrarHueco(Long cursoId, Integer ordenEliminado);
}