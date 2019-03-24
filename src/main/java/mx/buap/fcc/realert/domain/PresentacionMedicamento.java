package mx.buap.fcc.realert.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Carlos Montoya
 * @since 23/03/2019
 */
@Entity
@Data
public class PresentacionMedicamento
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private Medicamento medicamento;

	private String nombre;
	private String cantidad;
}
