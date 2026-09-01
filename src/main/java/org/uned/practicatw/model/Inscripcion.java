package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Matrícula de un {@link Estudiante} en un {@link Curso}. Entidad de primera
 * clase (no una simple tabla de unión): tiene su propio {@code id} y
 * {@code fechaInscripcion}, y de ella cuelgan tanto las {@link EntregaTarea}
 * del alumno para ese curso como, opcionalmente, su {@link Valoracion}.
 * <p>
 * La combinación {@code (estudiante_id, curso_id)} es única: un estudiante
 * no puede inscribirse dos veces en el mismo curso.
 * <p>
 * Tanto {@code estudiante} como {@code curso} llevan {@code @OnDelete(CASCADE)}:
 * borrar cualquiera de los dos arrastra la inscripción, y con ella (por
 * cascada adicional desde esta entidad) sus entregas y valoración.
 */
@Entity
@Table(
        name = "inscripciones",
        uniqueConstraints = @UniqueConstraint(
                name = "inscripcion_pk",
                columnNames = {"estudiante_id", "curso_id"}
        )
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQuery(
        name = "Inscripcion.buscarEstudiantesPorCurso",
        query = "SELECT i.estudiante FROM Inscripcion i WHERE i.curso.id = :cursoId"
)
@NamedQuery(
        name = "Inscripcion.buscarCursosPorEstudiante",
        query = "SELECT i.curso FROM Inscripcion i WHERE i.estudiante.id = :estudianteId"
)
@NamedQuery(
        name = "Inscripcion.buscarPorCursoAndEstudiante",
        query = "SELECT i FROM Inscripcion i " +
                "LEFT JOIN FETCH i.valoracion " +
                "WHERE i.curso.id = :cursoId AND i.estudiante.id = :estudianteId"
)
@NamedQuery(
        name = "Inscripcion.buscarPorEstudiante",
        query = "SELECT i FROM Inscripcion i " +
                "JOIN FETCH i.curso c " +
                "WHERE i.estudiante.id = :estudianteId " +
                "ORDER BY i.fechaInscripcion DESC"
)
@NamedQuery(
        name = "Inscripcion.buscarPorCurso",
        query = "SELECT i FROM Inscripcion i " +
                "JOIN FETCH i.estudiante " +
                "WHERE i.curso.id = :cursoId " +
                "ORDER BY i.fechaInscripcion DESC"
)
public class Inscripcion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "estudiante_id",
            foreignKey = @ForeignKey(name = "fk_inscripcion_estudiante")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Estudiante estudiante;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "curso_id",
            foreignKey = @ForeignKey(name = "fk_inscripcion_curso")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Curso curso;

    @Column(name = "fecha_inscripcion")
    private LocalDate fechaInscripcion;

    @OneToMany(mappedBy = "inscripciones", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntregaTarea> entregaTareas = new ArrayList<>();

    /** Valoración del alumno para este curso, si la ha hecho (relación 0..1, ver {@link Valoracion}). */
    @OneToOne(mappedBy = "inscripcion", fetch = FetchType.LAZY)
    private Valoracion valoracion;

    /**
     * Fecha de inscripción formateada como {@code dd/MM/yyyy} para mostrar
     * directamente en las vistas JSP, sin necesidad de formatear la
     * {@link LocalDate} desde EL/JSTL.
     *
     * @return la fecha formateada, o cadena vacía si {@code fechaInscripcion} es {@code null}
     */
    public String getFechaInscripcionFormateada() {
        return fechaInscripcion != null
                ? fechaInscripcion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : "";
    }
}