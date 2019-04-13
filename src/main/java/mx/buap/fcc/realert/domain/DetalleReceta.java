package mx.buap.fcc.realert.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Carlos Montoya
 * @since 23/03/2019
 */
@Entity
@Data
public class DetalleReceta
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private Receta receta;

	@ManyToOne(cascade = CascadeType.ALL)
	private Medicamento medicamento;

	private int dosis;
	private int frecuenciaHoras;
	private int duracionDias;
	private String mensaje;
}
