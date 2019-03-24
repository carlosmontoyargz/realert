package mx.buap.fcc.realert.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

	private String nombre;
	private String formula;

	@OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<Presentacion> presentaciones = new ArrayList<>();

	public void agregarPresentacion(Presentacion p)
	{
		presentaciones.add(p);
		p.setMedicamento(this);
	}
}
