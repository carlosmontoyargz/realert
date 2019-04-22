package mx.buap.fcc.realert.repository;

import mx.buap.fcc.realert.domain.Medico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Carlos Montoya
 * @since 22/04/2019
 */
@Repository
public interface MedicoRepository extends CrudRepository<Medico, Integer>
{
}
