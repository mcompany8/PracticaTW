package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        query = "SELECT i FROM Inscripcion i WHERE i.curso.id = :cursoId AND i.estudiante.id = :estudianteId"
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
    private Estudiante estudiante;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "curso_id",
            foreignKey = @ForeignKey(name = "fk_inscripcion_curso")
    )
    private Curso curso;

    @Column(name = "fecha_inscripcion")
    private LocalDate fechaInscripcion;

    @OneToMany(mappedBy = "inscripciones", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntregaTarea> entregaTareas = new ArrayList<>();
}
