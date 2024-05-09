package pe.gob.sunass.marcacion.tempus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.sunass.marcacion.tempus.repository.TempusMarcacionRepository;

@Service
public class TempusMarcacionService {
    
    @Autowired
    private TempusMarcacionRepository tempusMarcacionRepository;

    public List<Object[]> llamarStoredProcedure(String nrodoc, String fecha_ini, String fecha_fin){
        return tempusMarcacionRepository.llamarStoredProcedure(nrodoc, fecha_ini, fecha_fin);
    }
}
