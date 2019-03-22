package mx.buap.fcc.realert.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Carlos Montoya
 * @since 22/03/2019
 */
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Paciente extends Persona
{
	@ManyToOne
	private Expediente expediente;

	private String comentarios;
}
