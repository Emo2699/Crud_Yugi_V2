package yugiV2.yugiV2.controllers.api.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yugiV2.yugiV2.models.v1.Monstruo;
import yugiV2.yugiV2.services.v2.MonstruoServicePagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2")
public class MonstruoControllerPagination {
    @Autowired
    private MonstruoServicePagination service;


    /*Metodo para listar registros*/
    @GetMapping("/monstruos/sort")
    public ResponseEntity<Map<String, Object>> listarMonstruos(@RequestParam(defaultValue = "id,asc") String[] sort){
        try{
            String sortField = sort[0];
            Sort.Direction sortDirection = Sort.Direction.fromString(sort[1]);

            Sort sortOrder = Sort.by(sortDirection, sortField);
            List<Monstruo> monstruos = this.service.getMonstruosPag(sortOrder);

            Map<String, Object> response = new HashMap<>();


            if(monstruos.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                response.put("data", monstruos);
                response.put("status", HttpStatus.OK);

                return new ResponseEntity<>(response,HttpStatus.OK);
            }
        }catch (Exception e){
            Map<String,Object> json = new HashMap<>();

            json.put("message","Error en el servidor");
            json.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity<>(json,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
