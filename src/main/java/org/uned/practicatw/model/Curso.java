package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQueries(
        {
                @NamedQuery(
                        name = "Curso.buscarPorProfesor",
                        query = "SELECT c FROM Curso c WHERE c.responsable.id = :responsableId ORDER BY c.titulo"
                ),
                @NamedQuery(
                        name = "Curso.buscarPorIdYProfesor",
                        query = "SELECT c FROM Curso c WHERE c.responsable.id = :responsableId AND c.id = :id"
                )
        }
)
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    @Column(name = "duracion_horas")
    private Integer duracionHoras;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Nivel nivel;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarea> tareas = new ArrayList<>();

    @ManyToMany
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
    private List<Tematica> tematicas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(
            name = "responsable_id",
            foreignKey = @ForeignKey(name = "fk_responsable")
    )
    private Profesor responsable;
    private String imagen;

    @ManyToMany
    @JoinTable(
            name = "cursos_contenidos",
            joinColumns = @JoinColumn(
                    name = "curso_id",
                    foreignKey = @ForeignKey(name = "fk_curso"),
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "contenido_id",
                    foreignKey = @ForeignKey(name = "fK_contenido"),
                    nullable = false
            )
    )
    private List<Contenido> contenidos = new ArrayList<>();


}
