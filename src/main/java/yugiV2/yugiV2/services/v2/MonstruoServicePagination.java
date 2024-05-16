package yugiV2.yugiV2.services.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yugiV2.yugiV2.models.v1.Monstruo;
import yugiV2.yugiV2.repository.v1.MonstruoDAO;

import java.util.List;

@Service
public class MonstruoServicePagination {
    @Autowired
    private MonstruoDAO monstruoDAO;

    public List<Monstruo> getMonstruosPag(Sort sort){
        return monstruoDAO.findAll(sort);
    }
}
