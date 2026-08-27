package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contenidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contenido {

    public enum TipoContenido {
        ARCHIVO, URL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "tipo_contenido",
            length = 16,
            nullable = false
    )
    private TipoContenido tipoContenido;

    @Column(
            name = "fichero_id",
            nullable = false,
            unique = true
    )
    private String ficheroId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "propietario_id",
            foreignKey = @ForeignKey(name = "fk_propietario")
    )
    private Profesor propietario;

    @Column(nullable = false)
    private boolean publico;

    @Column(
            name = "fecha_subida",
            nullable = false
    )
    private LocalDateTime fechaSubida;




}
