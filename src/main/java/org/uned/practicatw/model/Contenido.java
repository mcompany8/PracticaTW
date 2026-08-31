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
@NamedQuery(
        name = "Contenido.buscarPorPropietarioOrPublico",
        query = "SELECT c FROM Contenido c WHERE c.propietario.id = :propietarioId OR c.publico = true ORDER BY titulo ASC",
        resultClass =  Contenido.class
)
public class Contenido {

    public enum TipoContenido {
        ARCHIVO, URL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(
            nullable = false,
            unique = true
    )
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "tipo_contenido",
            length = 16,
            nullable = false
    )
    private TipoContenido tipoContenido;

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
