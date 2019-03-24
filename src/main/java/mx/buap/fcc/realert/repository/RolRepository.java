package mx.buap.fcc.realert.repository;

import mx.buap.fcc.realert.domain.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Carlos Montoya
 * @since 24/03/2019
 */
@Repository
public interface RolRepository extends CrudRepository<Rol, Integer>
{
	Rol findByAuthority(String authority);
}
