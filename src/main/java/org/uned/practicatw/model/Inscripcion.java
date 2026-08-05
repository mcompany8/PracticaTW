package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inscripciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_estudiante",
            foreignKey = @ForeignKey(name = "fk_inscripcion_estudiante")
    )
    private Estudiante estudiante;

    @OneToMany(mappedBy = "inscripciones", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntregaTarea> entregaTareas = new ArrayList<>();
}
