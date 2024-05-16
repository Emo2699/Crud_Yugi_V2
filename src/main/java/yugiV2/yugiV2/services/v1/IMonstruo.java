package yugiV2.yugiV2.services.v1;

import yugiV2.yugiV2.models.v1.Monstruo;

import java.util.List;

public interface IMonstruo {

    List<Monstruo> getMonstruos();
    Monstruo getMonstruo(String id);

    int crearMonstruo(Monstruo monstruo);
    boolean actualizarMonstruo(String id, Monstruo monstruo);

    boolean deleteMonstruo(String id);
}
