package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private String descripcion;
    @Column(name = "duracion_horas")
    private Integer duracionHoras;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Nivel nivel;
    @OneToMany(mappedBy="curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarea> tareas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "cursos_tematicas",
            joinColumns = @JoinColumn(
                    name = "curso_id",
                    foreignKey = @ForeignKey(name = "fk_tematica"),
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "tematica_id",
                    foreignKey = @ForeignKey(name = "fk_curso"),
                    nullable = false
            )
    )
    private List<Tematica> tematicas = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "responsable_id")
    private Profesor responsable;
}
