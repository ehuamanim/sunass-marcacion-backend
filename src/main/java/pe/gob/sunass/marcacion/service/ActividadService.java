package pe.gob.sunass.marcacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.sunass.marcacion.model.Actividad;
import pe.gob.sunass.marcacion.repository.ActividadRepository;

@Service
public class ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    public List<Actividad> listAll(){
    	return actividadRepository.findAll();
    }

}
