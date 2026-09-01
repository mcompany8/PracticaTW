package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Usuario con rol de estudiante: puede inscribirse en {@link Curso}, marcar
 * {@link Tematica} de interés (vía {@link EstudianteTematica}) y valorar los
 * cursos en los que está inscrito.
 *
 * @implNote {@code inscripciones} es un {@code @OneToMany} <b>sin</b> {@code mappedBy}: al no declarar
 * el lado propietario de la relación con {@link Inscripcion#getEstudiante()},
 * Hibernate genera una tabla intermedia adicional en vez de reutilizar la FK
 * {@code inscripciones.estudiante_id} que ya existe. La colección queda ahí
 * como aviso — no se usa en ningún {@code Service}/{@code Command} de la
 * aplicación, que siempre consultan las inscripciones de un estudiante a
 * través de {@code InscripcionService.obtenerPorEstudiante(...)}.
 */
@Entity
@DiscriminatorValue(value = "ESTUDIANTE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Estudiante extends Usuario {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscripcion> inscripciones = new ArrayList<>();
}