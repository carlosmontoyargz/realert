package mx.buap.fcc.realert.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Carlos Montoya
 * @since 22/03/2019
 */
@Entity
@Data
public class Expediente
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String comentarios;
}
