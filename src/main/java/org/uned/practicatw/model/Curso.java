package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Curso ofertado en la plataforma, con su información descriptiva, nivel,
 * temáticas asociadas y el profesor responsable.
 * <p>
 * Al eliminar un {@code Curso}, el grafo de dependencias se resuelve con una
 * combinación de cascada de JPA y cascada a nivel de base de datos:
 * <ul>
 *     <li>{@code tareas}: cascada de JPA ({@code cascade = ALL, orphanRemoval = true}) —
 *     Hibernate borra cada {@link Tarea} explícitamente al borrar el curso.</li>
 *     <li>{@code tematicas}: {@code @OnDelete(CASCADE)} a nivel de base de datos sobre
 *     la FK {@code curso_id} de la tabla intermedia {@code cursos_tematicas} — solo
 *     cubre el borrado del curso, no el de una {@link Tematica} todavía asignada
 *     a algún curso (eso se bloquea explícitamente en {@code EliminarTematicaCommand}).</li>
 *     <li>{@link Contenido} e {@link Inscripcion}: cada una declara su propio
 *     {@code @OnDelete(CASCADE)} sobre la FK hacia {@code Curso} (ver esas clases).</li>
 * </ul>
 */
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
    /** Descripción corta, mostrada en tarjetas de catálogo y listados. */
    private String descripcion;

    /** Descripción extendida, mostrada en el detalle del curso. {@code TEXT} en vez de
     *  {@code @Lob} porque {@code @Lob} sobre {@code String} mapea a {@code oid} en PostgreSQL,
     *  lo que da problemas de lectura/escritura. */
    @Column(columnDefinition = "TEXT")
    private String descripcionLarga;

    @Column(name = "duracion_horas")
    private Integer duracionHoras;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Nivel nivel;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarea> tareas = new ArrayList<>();

    /** Temáticas del curso. Relación gestionada por reemplazo completo de la lista
     *  (borra-y-recrea las filas de {@code cursos_tematicas}) en {@code ActualizarCursoCommand}/
     *  {@code GuardarCursoCommand}, no con altas/bajas individuales. */
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

    /** Profesor responsable del curso. Sin {@code @OnDelete}: si un profesor con
     *  cursos asignados intenta eliminarse, se bloquea en {@code EliminarUsuarioAdminCommand}
     *  en vez de dejar cursos sin responsable. */
    @ManyToOne
    @JoinColumn(
            name = "responsable_id",
            foreignKey = @ForeignKey(name = "fk_responsable")
    )
    private Profesor responsable;
    /** Nombre de fichero de la imagen de portada, bajo {@code IMAGENES_DIR/cursos}
     *  (servida por {@code ImagenServlet} en {@code /imagenes/cursos/<imagen>}). */
    private String imagen;

}