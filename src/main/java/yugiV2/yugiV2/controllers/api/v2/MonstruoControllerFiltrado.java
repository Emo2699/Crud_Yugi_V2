package yugiV2.yugiV2.controllers.api.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yugiV2.yugiV2.models.v1.Monstruo;
import yugiV2.yugiV2.services.v1.impl.MonstruoService;

import java.util.List;

@RestController
@RequestMapping("api/v1/filtrado")
public class MonstruoControllerFiltrado {
@Autowired
    private MonstruoService ser;

@GetMapping("/ataques")
public ResponseEntity<?> buscarNombre(@RequestBody Monstruo nombre ){
    List<Monstruo> lis = ser.buscarPorNombre(nombre.getNombre());

    if(lis.isEmpty()){
        return ResponseEntity.status(HttpStatus.OK).body("No hay nombres de cartas con ese nombre");

    }else{
        return ResponseEntity.status(HttpStatus.OK).body(lis);
    }
}


}
