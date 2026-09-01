package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQuery(
        name = "Curso.buscarPorProfesor",
        query = "SELECT c FROM Curso c WHERE c.responsable.id = :responsableId ORDER BY c.titulo"
)
@NamedQuery(
        name = "Curso.buscarPorIdYProfesor",
        query = "SELECT c FROM Curso c " +
                "LEFT JOIN FETCH c.tematicas " +
                "WHERE c.responsable.id = :responsableId AND c.id = :id"
)
@NamedQuery(
        name = "Curso.buscarCursosRandom",
        query = "SELECT c FROM Curso c LEFT JOIN FETCH c.tematicas ORDER BY FUNCTION('RANDOM') "
)
@NamedQuery(
        name = "Curso.buscarTodosConTematicas",
        query = "SELECT DISTINCT c FROM Curso c LEFT JOIN FETCH c.tematicas ORDER BY c.titulo"
)
@NamedQuery(
        name = "Curso.buscarPorTematica",
        query = "SELECT DISTINCT c FROM Curso c " +
                "LEFT JOIN FETCH c.tematicas " +
                "JOIN c.tematicas tFiltro " +
                "WHERE tFiltro.id = :tematicaId " +
                "ORDER BY c.titulo"
)
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;

    @Column(columnDefinition = "TEXT")
    private String descripcionLarga;

    @Column(name = "duracion_horas")
    private Integer duracionHoras;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Nivel nivel;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarea> tareas = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cursos_tematicas",
            joinColumns = @JoinColumn(
                    name = "curso_id",
                    foreignKey = @ForeignKey(name = "fk_curso"),
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "tematica_id",
                    foreignKey = @ForeignKey(name = "fk_tematica"),
                    nullable = false
            )
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Tematica> tematicas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(
            name = "responsable_id",
            foreignKey = @ForeignKey(name = "fk_responsable")
    )
    private Profesor responsable;
    private String imagen;

}
