package yugiV2.yugiV2.repository.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yugiV2.yugiV2.models.v1.Monstruo;

import java.util.List;


@Repository
public interface MonstruoDAO extends JpaRepository<Monstruo,String> {
    public List <Monstruo> findByNombreIgnoringCaseContaining(String nombre);

}
