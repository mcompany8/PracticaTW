package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

/**
 * Entidad de unión explícita entre {@link Estudiante} y {@link Tematica},
 * representando el interés de un estudiante por un área formativa concreta.
 * <p>
 * Se modela como entidad propia (en vez de un {@code @ManyToMany} implícito
 * con {@code @JoinTable}, como sí se hace en {@code Curso.tematicas}) precisamente
 * para poder declarar {@code @OnDelete(CASCADE)} de forma independiente en
 * <b>cada</b> lado de la relación: borrar un {@link Estudiante} limpia sus
 * filas aquí, y borrar una {@link Tematica} también — algo que un
 * {@code @ManyToMany} implícito no permite, porque esa anotación solo cubre
 * la FK que apunta al lado que declara el mapeo, no las dos.
 * <p>
 * La combinación {@code (estudiante_id, tematica_id)} es única.
 */
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