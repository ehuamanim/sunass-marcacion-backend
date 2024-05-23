package pe.gob.sunass.marcacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.sunass.marcacion.model.InstitucionSede;
import pe.gob.sunass.marcacion.repository.InstitucionSedeRepository;

@Service
public class InstitucionService {

    @Autowired
    private InstitucionSedeRepository institucionRepository;

    public List<InstitucionSede> listAll(){
        return institucionRepository.findAll();
    }
    
    public Long count(){
        return institucionRepository.count();
    }
    
    public InstitucionSede save(InstitucionSede inst){
        return institucionRepository.save(inst);
    }
    
    public InstitucionSede update(InstitucionSede inst){
        return institucionRepository.save(inst);
    }
    
    public InstitucionSede getById(String id){
        return institucionRepository.findById(id).orElse(null);
    }
}
