package pe.gob.sunass.marcacion.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.sunass.marcacion.common.FechaUtil;
import pe.gob.sunass.marcacion.model.PersonalRemoto;
import pe.gob.sunass.marcacion.repository.PersonalRemotoRepository;

@Service
public class PersonalRemotoService {

    @Autowired
    private PersonalRemotoRepository personalRemotoRepository;

    public boolean personalTieneDiaAsignado( String personalId ){
        Date fechaActual = FechaUtil.getDateWithoutHours(new Date());
    	List<PersonalRemoto> pr = personalRemotoRepository.findByPersonalAndFechaIniRemoto( personalId, fechaActual );
        return (pr != null) && (pr.size() > 0);
    }

}
