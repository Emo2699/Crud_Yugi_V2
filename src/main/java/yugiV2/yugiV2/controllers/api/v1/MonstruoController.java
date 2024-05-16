package yugiV2.yugiV2.controllers.api.v1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yugiV2.yugiV2.models.v1.Monstruo;
import yugiV2.yugiV2.services.v1.impl.MonstruoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class MonstruoController {

    @Autowired
    private MonstruoService service;


    /*Metodo para listar registros*/
    @GetMapping("monstruos")
    public ResponseEntity<Map<String, Object>> listarMonstruos(){
        try{
            List<Monstruo> query = this.service.getMonstruos();
            Map<String,Object> json = new HashMap<>();

            if(query.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                json.put("data",query);
                json.put("status", HttpStatus.OK);

                return new ResponseEntity<>(json,HttpStatus.OK);
            }
        }catch (Exception e){
            Map<String,Object> json = new HashMap<>();

            json.put("message","Error en el servidor");
            json.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<>(json,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*Buscar por ID*/

    @GetMapping("monstruos/{id}")
    public ResponseEntity<Map<String,Object>> buscaMonstruo(@PathVariable String id){
        try{
            Monstruo query = this.service.getMonstruo(id);
            Map<String,Object> json = new HashMap<>();
            if(query == null){
                json.put("message","No se encontro registro con ese id");
                json.put("status",HttpStatus.BAD_REQUEST);

                return new ResponseEntity<>(json,HttpStatus.BAD_REQUEST);
            }else{
                json.put("data",query);
                json.put("status",HttpStatus.OK);

                return new ResponseEntity<>(json,HttpStatus.OK);
            }
        }catch(Exception e){
            Map<String,Object> json = new HashMap<>();

            json.put("message","Error en el servidor");
            json.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<>(json,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("monstruos")
    public ResponseEntity<Map<String,Object>> agregarCarta(@RequestBody Monstruo monstruo){
        try{
            int resultado = this.service.crearMonstruo(monstruo);
            System.out.println(resultado);
            Map<String,Object> json = new HashMap<>();
            if(resultado == 1){
                json.put("message","Monstruo agregado correctamente...");
                json.put("status",HttpStatus.CREATED);
                return new ResponseEntity<>(json,HttpStatus.CREATED);
            } else if(resultado == 0){
                json.put("message","Ya existe un registro con esa informacion....");
                json.put("status",HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(json,HttpStatus.BAD_REQUEST);
            }else{
                json.put("message","Error al agregar registro....");
                json.put("status",HttpStatus.INTERNAL_SERVER_ERROR);

                return new ResponseEntity<>(json,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(Exception e){
            Map<String,Object> json = new HashMap<>();

            json.put("message","Error en el servidor");
            json.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<>(json,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("monstruos/{id}")
    public ResponseEntity<Map<String,Object>> actualizarInfoCarta(@PathVariable String id, @RequestBody Monstruo monstruo) {
        try{
            Map<String,Object> json = new HashMap<>();

            boolean resultado = this.service.actualizarMonstruo(id,monstruo);

            if(resultado){
                json.put("message","Monstruo actualizado correctamente...");
                json.put("status",HttpStatus.OK);
                return new ResponseEntity<>(json,HttpStatus.OK);
            }else{
                json.put("message","Error al actualizar la carta...");
                json.put("status",HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(json,HttpStatus.BAD_REQUEST);
            }

        }catch(Exception e){
            Map<String,Object> json = new HashMap<>();
            json.put("message","Error en el servidor");
            json.put("status",HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("monstruos/{id}")
    public ResponseEntity<Map<String,Object>> eliminarMonstruo(@PathVariable String id){
        try{
            boolean result = this.service.deleteMonstruo(id);
            Map<String,Object> json = new HashMap<>();

            if(result){
                json.put("message","Monstruo eliminado correctamente...");
                json.put("status",HttpStatus.OK);
                return new ResponseEntity<>(json,HttpStatus.OK);
            }else{
                json.put("message","Error al eliminar...");
                json.put("status", HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(json,HttpStatus.BAD_REQUEST);
            }

        }catch(Exception e){
            Map<String,Object> json = new HashMap<>();

            json.put("message","Error en el servidor");
            json.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<>(json,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
