package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Configuración global de la plataforma, editable por un administrador desde
 * {@code EditarConfiguracionCommand}/{@code GuardarConfiguracionCommand}
 * (texto e imagen del hero de la portada, número de cursos recomendados
 * mostrados en el inicio).
 * <p>
 * Fila única: el {@code id} es fijo ({@code 1L}) y no autogenerado, ya que no
 * tiene sentido "varias configuraciones" — no hay más consulta que
 * {@code obtenerPorId(1L)}. La fila se crea una única vez al arrancar la
 * aplicación si no existe (ver {@code SeedListener.seedConfiguracion()}).
 */
@Entity
@Table(name = "configuracion_sistema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfiguracionSistema {

    @Id
    private Long id;

    private String heroTitulo;

    @Column(name = "hero_subtitulo", columnDefinition = "TEXT")
    private String heroSubtitulo;

    /** Nombre de fichero de la imagen de fondo del hero, bajo {@code IMAGENES_DIR}
     *  directamente (no en una subcarpeta, a diferencia de las imágenes de curso/temática). */
    @Column(name = "hero_imagen")
    private String heroImagen;

    /** Cuántos cursos aleatorios se muestran como "destacados" en la portada. Acotado
     *  a [1, 12] al guardar, en {@code GuardarConfiguracionCommand}. */
    @Column(name = "num_cursos_recomendados", nullable = false)
    private Integer numCursosRecomendados;
}