package mx.buap.fcc.realert.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Carlos Montoya
 * @since 22/03/2019
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Persona
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nombre;
	private String correo;
	private String password;
	private String telefono;

	@Column(updatable = false)
	private LocalDate registro;

	@Enumerated(EnumType.STRING)
	@Column(updatable = false)
	private Rol rol;

	@PrePersist
	public void setFechaRegistro()
	{
		registro = LocalDate.now();
	}

	/*@ManyToOne(cascade = CascadeType.ALL)
	private Rol rol;*/
}
