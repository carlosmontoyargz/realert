package mx.buap.fcc.realert.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Carlos Montoya
 * @since 23/03/2019
 */
@Entity
@Data
public class Receta
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private Medico medico;

	@ManyToOne
	private Paciente paciente;

	@OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<DetalleReceta> detalles = new ArrayList<>();

	private LocalDate fecha;

	public void agregarDetalle(DetalleReceta d)
	{
		detalles.add(d);
		d.setReceta(this);
	}
}
