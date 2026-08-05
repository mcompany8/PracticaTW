package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "entrega_tareas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntregaTarea implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "inscripcion_id",
            foreignKey =  @ForeignKey(name = "fk_entregatarea_inscripcion")
    )
    private Inscripcion inscripciones;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "tarea_id",
            foreignKey = @ForeignKey(name = "fk_entregatarea_tarea")
    )
    private Tarea tarea;

}
