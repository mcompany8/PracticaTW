package org.uned.practicatw.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "cursos_contenidos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(
        name = "CursoContenido.buscarPorCursoYContenido",
        query = "SELECT c FROM CursoContenido c WHERE c.curso.id = :cursoId AND c.contenido.id = :contenidoId",
        resultClass = CursoContenido.class
)
@NamedQuery(
        name = "CursoContenido.buscarPorNotCurso",
        query = "SELECT c FROM CursoContenido c WHERE c.curso.id != :cursoId",
        resultClass = CursoContenido.class
)
public class CursoContenido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "contenido_id",
            foreignKey = @ForeignKey(name = "fk_contenido")
    )
    @NonNull
    private Contenido contenido;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "curso_id",
            foreignKey = @ForeignKey(name = "fk_curso")
    )
    @NonNull
    private Curso curso;

    private String descripcion;
}
