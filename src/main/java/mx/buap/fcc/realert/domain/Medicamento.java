package mx.buap.fcc.realert.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Carlos Montoya
 * @since 23/03/2019
 */
@Entity
@Data
public class Medicamento
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/*@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "detalle_receta_id", referencedColumnName = "id")
	private DetalleReceta detalleReceta;*/

	private String nombre;
	private String presentacion;
	private String formula;
	private String viaAdministracion;

	/*@OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<PresentacionMedicamento> presentaciones = new ArrayList<>();

	public void agregarPresentacion(PresentacionMedicamento p)
	{
		presentaciones.add(p);
		p.setMedicamento(this);
	}*/
}
