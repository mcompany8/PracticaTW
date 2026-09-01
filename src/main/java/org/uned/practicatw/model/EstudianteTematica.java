package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Table(
        name = "estudiantes_tematicas",
        uniqueConstraints = @UniqueConstraint(
                name = "estudiantetematica_uk",
                columnNames = {"estudiante_id", "tematica_id"}
        )
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQuery(
        name = "EstudianteTematica.buscarPorEstudiante",
        query = "SELECT et FROM EstudianteTematica et " +
                "LEFT JOIN FETCH et.tematica " +
                "WHERE et.estudiante.id = :estudianteId " +
                "ORDER BY et.tematica.titulo"

)
public class EstudianteTematica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "estudiante_id",
            foreignKey = @ForeignKey(name = "fk_estudiantetematica_estudiante")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "tematica_id",
            foreignKey = @ForeignKey(name = "fk_estudiantetematica_tematica")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Tematica tematica;
}