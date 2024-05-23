package pe.gob.sunass.marcacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.sunass.marcacion.model.Planilla;
import pe.gob.sunass.marcacion.repository.PlanillaRepository;

@Service
public class PlanillaService {

    @Autowired
    private PlanillaRepository planillaRepository;

    public List<Planilla> listAll(){
        return planillaRepository.findAll();
    }
    
    public Long count(){
        return planillaRepository.count();
    }
    
    public Planilla save(Planilla pla){
        return planillaRepository.save(pla);
    }
    
    public Planilla update(Planilla pla){
        return planillaRepository.save(pla);
    }
    
    public Planilla getById(String id){
        return planillaRepository.findById(id).orElse(null);
    }
}
