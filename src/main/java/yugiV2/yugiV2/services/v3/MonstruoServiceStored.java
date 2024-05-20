package yugiV2.yugiV2.services.v3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yugiV2.yugiV2.repository.MonstruoRepository;

import java.util.Map;

@Service
public class MonstruoServiceStored {
    @Autowired
    private MonstruoRepository monstruoRepository;

    public Map<String, Object> crearMonstruo(int id, String nombre, String atributo, int nivel, int ataque, int defensa) {
        return monstruoRepository.crearMonstruo(id, nombre, atributo, nivel, ataque, defensa);
    }

    public Map<String, Object> leerMonstruo(int id) {
        return monstruoRepository.leerMonstruo(id);
    }

    public Map<String, Object> listarMonstruos() {
        return  monstruoRepository.listarMonstruos();
    }

    public Map<String, Object> actualizarMonstruo(int id, String nombre, String atributo, int nivel, int ataque, int defensa) {
        return monstruoRepository.actualizarMonstruo(id, nombre, atributo, nivel, ataque, defensa);
    }

    public Map<String, Object> eliminarMonstruo(int id) {
        return monstruoRepository.eliminarMonstruo(id);
    }
}

