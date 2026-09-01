package org.uned.practicatw.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Usuario con rol de administrador. No añade ningún campo propio: hereda de
 * {@link Profesor} para poder gestionar cursos igual que un profesor normal,
 * y además tiene acceso a la gestión global de usuarios, temáticas y
 * configuración de la plataforma (ver {@code AdminCommand} y los {@code Command}
 * bajo {@code *UsuarioAdmin}/{@code *Tematica}/{@code *Configuracion}).
 * <p>
 * Requiere {@code @NoArgsConstructor} explícito (no basta con
 * {@code @AllArgsConstructor}, que por sí solo suprime el constructor vacío
 * que Lombok generaría por defecto): sin él, Hibernate no puede instanciar
 * esta clase por reflexión al leer una fila con {@code tipo_usuario = 'ADMINISTRADOR'}.
 */
@Entity
@DiscriminatorValue(value = "ADMINISTRADOR")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Administrador extends Profesor {
}