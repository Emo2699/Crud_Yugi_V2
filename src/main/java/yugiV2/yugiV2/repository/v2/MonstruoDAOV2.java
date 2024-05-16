package yugiV2.yugiV2.repository.v2;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yugiV2.yugiV2.models.v1.Monstruo;


@Repository
public interface MonstruoDAOV2 extends JpaRepository<Monstruo, String> {

    /*Implementamos el metodo de listas*/
    @Query("SELECT m FROM Monstruo m")
    Page<Monstruo> getAllMonsters(Pageable pageable);
}
