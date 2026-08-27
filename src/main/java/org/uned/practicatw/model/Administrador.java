package org.uned.practicatw.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue(value = "ADMINISTRADOR")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class Administrador extends Profesor{
}
