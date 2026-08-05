package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tareas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tarea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    @Column(name = "plazo_dias", nullable = false)
    private int plazoDias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "curso_id",
            foreignKey = @ForeignKey(name = "fk_tarea_curso")
    )
    private Curso curso;

    @OneToMany(mappedBy = "tarea")
    private List<EntregaTarea> entregaTareas;

}
