package mx.buap.fcc.realert.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
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

	@OneToMany(mappedBy = "receta")
	private List<DetalleReceta> detalles;

	private LocalDate fecha;
}
