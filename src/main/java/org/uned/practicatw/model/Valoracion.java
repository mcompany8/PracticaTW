package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

/**
 * Valoración de un estudiante sobre un curso: puntuación de 1 a 5 y comentario
 * opcional. Relación {@code @OneToOne} con {@link Inscripcion} (no con
 * {@link Estudiante}/{@link Curso} directamente): la unicidad de "como mucho
 * una valoración por inscripción" se expresa de forma natural como
 * {@code unique = true} sobre la FK {@code inscripcion_id}, sin necesitar una
 * unique constraint compuesta a nivel de tabla.
 * <p>
 * El rango 1–5 de {@code valoracion} no se valida aquí con anotaciones de Bean
 * Validation (el proyecto no usa esa dependencia en ningún otro sitio) sino en
 * la capa de {@code Command}/{@code Service} que la crea.
 */
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

    /** Puntuación de 1 a 5. */
    @Column(nullable = false)
    private Integer valoracion;

    @Column(columnDefinition = "TEXT")
    private String comentario;
}