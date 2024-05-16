package yugiV2.yugiV2.services.v1.impl;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yugiV2.yugiV2.models.v1.Monstruo;
import yugiV2.yugiV2.repository.v1.MonstruoDAO;
import yugiV2.yugiV2.services.v1.IMonstruo;

import java.util.List;
import java.util.Optional;


@Service
public class MonstruoService implements IMonstruo {
    //Inyeccion del Repositorio
    @Autowired
    private MonstruoDAO repositorio;


    @Override
    public List<Monstruo> getMonstruos() {
        return (List<Monstruo>) this.repositorio.findAll();
    }

    @Override
    public Monstruo getMonstruo(String id) {

        try{
            /*Si la cadena esta vacia*/
            if(id == null || id.isBlank()){
                return null;
            }
            else{
                Optional<Monstruo> resultado = this.repositorio.findById(id);
                return resultado.orElse(null);
            }

        }catch(Exception e){
            return null;
        }

    }

    @Transactional
    @Override
    public int crearMonstruo(Monstruo monstruo) {
        try{
            //Antes de agregar un nuevo mosntruo debo comprobar que no exista ya en la BD
            Optional<Monstruo> query = repositorio.findById(monstruo.getIdCard());

            if(query.isEmpty()){
                //No hay registros, lo agrego a la BD
                //this.repository.save(monster);
                this.repositorio.save(monstruo);
                return 1;
            }else{
                //Ya hay un registro, por lo que seria un duplicado
                return 0;
            }
        }catch(Exception e){
            return -1;
        }
    }

    @Transactional
    @Override
    public boolean actualizarMonstruo(String id, Monstruo monstruo) {
        try{
            if(id == null || id.isBlank()){return false;}

            //Hay que verificar primero si exista el registro a actualizar en la BD
            Optional<Monstruo> query = repositorio.findById(id);

            if(query.isEmpty()){
                //No se encontro registro en la BD, por lo que no se puede actualizar
                return false;
            }else{
                //Hacemos el update en la BD
                /*Tenemos que pasar primero el id con el que se encontro el registro
                 * y ya despues pasamos uno por uno todos los campos del nuevo objeto
                 * esto con la finalidad de que si se cambia el ID de la carta no hay problema al actualizar.
                 * */
                Monstruo actualizado = query.get();
                actualizado.setNombre(monstruo.getNombre());
                actualizado.setAtributo(monstruo.getAtributo());
                actualizado.setNivel(monstruo.getNivel());
                actualizado.setAtaque(monstruo.getAtaque());
                actualizado.setDefensa(monstruo.getDefensa());
                this.repositorio.save(actualizado);
                return true;
            }
        }catch(Exception e){
            return false;
        }
    }

    @Transactional
    @Override
    public boolean deleteMonstruo(String id) {
        //Antes de eliminar debo comprobar que exista  en la BD
        Optional<Monstruo> query = this.repositorio.findById(id);
        if(query.isEmpty()){
            //No existe el registro ern la base de datos, no se puede eliminar
            return false;
        }else{
            this.repositorio.deleteById(id);
            return true;
        }
    }

    public List<Monstruo> buscarPorNombre(String nombre){
        return repositorio.findByNombreIgnoringCaseContaining(nombre.toUpperCase());
    }

}
