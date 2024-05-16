package yugiV2.yugiV2.services.v2.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import yugiV2.yugiV2.models.v1.Monstruo;
import yugiV2.yugiV2.repository.v2.MonstruoDAOV2;
import yugiV2.yugiV2.services.v2.IMonstruoV2;


@Service
public class MonstruoServiceV2 implements IMonstruoV2 {

    @Autowired
    private MonstruoDAOV2 repositorio;



    @Override
    public Page<Monstruo> getMonstruos(Pageable pageable) {
        return this.repositorio.getAllMonsters(pageable);
    }
}
