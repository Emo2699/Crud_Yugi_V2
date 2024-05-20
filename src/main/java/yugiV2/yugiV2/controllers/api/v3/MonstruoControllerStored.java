package yugiV2.yugiV2.controllers.api.v3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yugiV2.yugiV2.services.v3.MonstruoServiceStored;

import java.util.Map;

@RestController
@RequestMapping("/monstruos/v3")
public class MonstruoControllerStored {
    @Autowired
    private MonstruoServiceStored monstruoService;

    @PostMapping("/crear")
    public Map<String, Object> crearMonstruo(@RequestParam int id, @RequestParam String nombre, @RequestParam String atributo, @RequestParam int nivel, @RequestParam int ataque, @RequestParam int defensa) {
        return monstruoService.crearMonstruo(id, nombre, atributo, nivel, ataque, defensa);
    }

    @GetMapping("/leer")
    public Map<String, Object> leerMonstruo(@RequestParam int id) {
        return monstruoService.leerMonstruo(id);
    }

    @GetMapping("/listar")
    public Map<String, Object> listarMonstruos(){
        return monstruoService.listarMonstruos();
    }

    @PutMapping("/actualizar")
    public Map<String, Object> actualizarMonstruo(@RequestParam int id, @RequestParam String nombre, @RequestParam String atributo, @RequestParam int nivel, @RequestParam int ataque, @RequestParam int defensa) {
        return monstruoService.actualizarMonstruo(id, nombre, atributo, nivel, ataque, defensa);
    }

    @DeleteMapping("/eliminar")
    public Map<String, Object> eliminarMonstruo(@RequestParam int id) {
        return monstruoService.eliminarMonstruo(id);
    }
}
