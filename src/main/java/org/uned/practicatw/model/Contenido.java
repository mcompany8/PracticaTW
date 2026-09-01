package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "contenidos",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "contenido_pk",
                        columnNames = {"curso_id","orden"}
                )
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQuery(
        name = "Contenido.buscarPorCurso",
        query = "SELECT c FROM Contenido c WHERE c.curso.id = :cursoId ORDER BY titulo ASC",
        resultClass =  Contenido.class
)
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(
            name = "fecha_subida",
            nullable = false
    )
    private LocalDateTime fechaSubida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "curso_id",
            foreignKey = @ForeignKey(name = "fk_curso")
    )
    private Curso curso;

    @Column(nullable = false)
    private Integer orden;

    @Column(length = 2048, nullable = false)
    private String uri;


}
