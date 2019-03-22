package mx.buap.fcc.realert.repository;

import mx.buap.fcc.realert.domain.Expediente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Carlos Montoya
 * @since 22/03/2019
 */
@Repository
public interface ExpedienteRepository extends CrudRepository<Expediente, Integer>
{
}
