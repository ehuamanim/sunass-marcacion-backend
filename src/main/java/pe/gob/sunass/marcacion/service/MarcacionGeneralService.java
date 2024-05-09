package pe.gob.sunass.marcacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pe.gob.sunass.marcacion.model.MarcacionGeneral;
import pe.gob.sunass.marcacion.repository.MarcacionGeneralRepository;

@Transactional
@Service
public class MarcacionGeneralService {

    @Autowired
    private MarcacionGeneralRepository marcacionGeneralRepository;

    public MarcacionGeneral save(MarcacionGeneral marcacionGeneral) {
        return marcacionGeneralRepository.save(marcacionGeneral);
    }

    public MarcacionGeneral findByItem( Long item ){
        return marcacionGeneralRepository.findByItem( item );
    }

    public List<MarcacionGeneral> findByPersCodigo( String persCodigo ){
        return marcacionGeneralRepository.findByPersCodigo( persCodigo );
    }

}
