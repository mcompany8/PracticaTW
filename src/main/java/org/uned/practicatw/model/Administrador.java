package org.uned.practicatw.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Administrador")
@Getter
@Setter
public class Administrador extends Profesor{
}
