package mx.buap.fcc.realert.domain;

import lombok.Data;
import lombok.ToString;

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
	@ToString.Exclude
	private Receta receta;

	@ManyToOne
	private PresentacionMedicamento presentacion;

	private int dosis;
	private String mensaje;
}
