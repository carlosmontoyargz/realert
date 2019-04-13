package mx.buap.fcc.realert.repository;

import mx.buap.fcc.realert.domain.DetalleReceta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Carlos Montoya
 * @since 11/04/2019
 */
@Repository
public interface DetalleRecetaRepository extends CrudRepository<DetalleReceta, Integer>
{
}
