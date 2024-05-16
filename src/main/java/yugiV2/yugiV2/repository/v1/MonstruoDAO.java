package yugiV2.yugiV2.repository.v1;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yugiV2.yugiV2.models.v1.Monstruo;


@Repository
public interface MonstruoDAO extends CrudRepository<Monstruo,String> {


}
