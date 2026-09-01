package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

/**
 * Material asociado a un {@link Curso} (documento, enlace externo...),
 * identificado por su {@code titulo} y localizado mediante {@code uri}
 * — el nombre de fichero guardado en {@code CONTENIDO_DIR} para archivos
 * subidos, o una URL completa para enlaces externos (distinguidos por
 * prefijo {@code http(s)://} en las vistas, no por un campo de tipo explícito).
 * <p>
 * {@code orden} determina la posición del material dentro de la lista del
 * curso, y está sujeto a la unique constraint {@code (curso_id, orden)}: no
 * puede haber dos materiales del mismo curso con el mismo orden. Insertar,
 * reordenar o eliminar un material implica desplazar el {@code orden} del
 * resto (ver {@code ContenidoDAO.hacerHueco}/{@code cerrarHueco}/{@code actualizarOrden}),
 * usando siempre una posición "sentinela" fuera de rango como paso
 * intermedio para no violar la constraint mientras se reordena.
 */
@Entity
@Table(
        name = "contenidos",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "contenido_pk",
                        columnNames = {"curso_id","orden"}
                )
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQuery(
        name = "Contenido.buscarPorCurso",
        query = "SELECT c FROM Contenido c WHERE c.curso.id = :cursoId ORDER BY orden ASC",
        resultClass =  Contenido.class
)
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(
            name = "fecha_subida",
            nullable = false
    )
    private LocalDateTime fechaSubida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "curso_id",
            foreignKey = @ForeignKey(name = "fk_curso")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Curso curso;

    /** Posición del material en la lista del curso (1-indexado). Ver la nota de
     *  clase sobre la unique constraint que lo acompaña. */
    @Column(nullable = false)
    private Integer orden;

    /** Nombre de fichero (si se subió un archivo) o URL completa (si es un enlace externo). */
    @Column(length = 2048, nullable = false)
    private String uri;


}