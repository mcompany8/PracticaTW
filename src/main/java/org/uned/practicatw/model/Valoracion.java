package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Table(name = "valoraciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQuery(
        name = "Valoracion.buscarPorInscripcion",
        query = "SELECT v FROM Valoracion v WHERE v.inscripcion.id = :inscripcionId"
)
@NamedQuery(
        name = "Valoracion.obtenerPorCurso",
        query = "SELECT v FROM Valoracion v " +
                "JOIN FETCH v.inscripcion i " +
                "JOIN FETCH i.estudiante " +
                "WHERE i.curso.id = :cursoId"
)
public class Valoracion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "inscripcion_id",
            nullable = false,
            unique = true,
            foreignKey = @ForeignKey(name = "fk_valoracion_inscripcion")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Inscripcion inscripcion;

    @Column(nullable = false)
    private Integer valoracion;

    @Column(columnDefinition = "TEXT")
    private String comentario;
}