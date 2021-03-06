package mx.buap.fcc.realert.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.PrePersist;

/**
 * @author Carlos Montoya
 * @since 22/03/2019
 */
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Medico extends Persona
{
	private String cedula;

	@PrePersist
	public void setRol()
	{
		super.setRol(Rol.MEDICO);
	}
}
