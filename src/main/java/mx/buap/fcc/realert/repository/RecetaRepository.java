package mx.buap.fcc.realert.repository;

import mx.buap.fcc.realert.domain.Receta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Carlos Montoya
 * @since 23/03/2019
 */
@Repository
public interface RecetaRepository extends CrudRepository<Receta, Integer>
{
	List<Receta> findByPacienteCorreo(String correo);

	List<Receta> findByMedicoCorreo(String correo);
}
