package mx.buap.fcc.realert.repository;

import mx.buap.fcc.realert.domain.Medicamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Carlos Montoya
 * @since 23/03/2019
 */
@Repository
public interface MedicamentoRepository extends CrudRepository<Medicamento, Integer>
{
}
