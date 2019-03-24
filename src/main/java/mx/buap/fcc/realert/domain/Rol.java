package mx.buap.fcc.realert.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Carlos Montoya
 * @since 24/03/2019
 */
@Entity
@Data
public class Rol
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String authority;
}
