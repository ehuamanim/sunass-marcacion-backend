package pe.gob.sunass.marcacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.sunass.marcacion.model.Ejercicio;
import pe.gob.sunass.marcacion.repository.EjercicioRepository;

@Service
public class EjercicioService {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    public List<Ejercicio> listAll(){
        return ejercicioRepository.findAll();
    }
    
    public Long count(){
        return ejercicioRepository.count();
    }
    
    public Ejercicio save(Ejercicio eje){
        return ejercicioRepository.save(eje);
    }
    
    public Ejercicio update(Ejercicio eje){
        return ejercicioRepository.save(eje);
    }
    
    public Ejercicio getById(String id){
        return ejercicioRepository.findById(id).orElse(null);
    }
}
