package mx.buap.fcc.realert.repository;

import mx.buap.fcc.realert.domain.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Carlos Montoya
 * @since 22/03/2019
 */
@Repository
public interface PersonaRepository extends CrudRepository<Persona, Integer>
{
}
