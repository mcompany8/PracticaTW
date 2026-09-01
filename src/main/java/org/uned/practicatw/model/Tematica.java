package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * Área temática o categoría formativa (p. ej. "Desarrollo Web y Móvil").
 * Se usa tanto como área de interés de un {@link Estudiante} (a través de
 * {@link EstudianteTematica}) como para clasificar los {@link Curso}
 * (a través de {@code Curso.tematicas}).
 * <p>
 * No se puede eliminar una temática todavía asignada a algún curso: la
 * tabla intermedia {@code cursos_tematicas} no tiene {@code @OnDelete}
 * sobre su columna {@code tematica_id} (solo sobre {@code curso_id}, para
 * el borrado de cursos), así que ese caso se bloquea explícitamente en
 * {@code EliminarTematicaCommand} antes de intentar el borrado.
 */
@Entity
@Table(name = "tematicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tematica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    /** Nombre de fichero de la imagen, bajo {@code IMAGENES_DIR/tematicas}. */
    private String imagen;

}