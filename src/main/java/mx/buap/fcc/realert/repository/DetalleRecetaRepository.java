package mx.buap.fcc.realert.repository;

import mx.buap.fcc.realert.domain.DetalleReceta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Carlos Montoya
 * @since 11/04/2019
 */
@Repository
public interface DetalleRecetaRepository extends CrudRepository<DetalleReceta, Integer>
{
	@Transactional
	@Modifying
	void deleteByRecetaId(int id);
}
