package mx.buap.fcc.realert.repository;

import mx.buap.fcc.realert.domain.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Carlos Montoya
 * @since 21/04/2019
 */
@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Integer>
{
}
