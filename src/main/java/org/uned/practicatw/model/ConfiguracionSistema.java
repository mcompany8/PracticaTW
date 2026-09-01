package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "configuracion_sistema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfiguracionSistema {

    // Fila única (id fijo = 1), no autogenerado: no hay "varias configuraciones",
    // así que no tiene sentido una secuencia para esto.
    @Id
    private Long id;

    @Column(name = "hero_titulo", nullable = false)
    private String heroTitulo;

    @Column(name = "hero_subtitulo", columnDefinition = "TEXT")
    private String heroSubtitulo;

    @Column(name = "hero_imagen")
    private String heroImagen;

    @Column(name = "num_cursos_recomendados", nullable = false)
    private Integer numCursosRecomendados;
}