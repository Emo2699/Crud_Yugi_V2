package yugiV2.yugiV2.services.v2;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import yugiV2.yugiV2.models.v1.Monstruo;
import yugiV2.yugiV2.repository.v2.MonstruoDAOV2;

public interface IMonstruoV2 {

    Page<Monstruo> getMonstruos(Pageable pageable);




}
