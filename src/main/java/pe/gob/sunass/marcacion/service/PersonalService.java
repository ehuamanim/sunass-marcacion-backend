package pe.gob.sunass.marcacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.gob.sunass.marcacion.dto.PersonalDto;
import pe.gob.sunass.marcacion.model.Personal;
import pe.gob.sunass.marcacion.repository.PersonalRepository;

@Service
public class PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    public List<PersonalDto> listAll(Pageable pageable) {
        return personalRepository.findPersonalAllByNroDoc(null, pageable);
    }

    public Personal findById(String personalId) {
        return personalRepository.findByPersonalId(personalId);
    }

    public Personal findByNroDoc(String nroDoc) {
        return personalRepository.findByNroDoc(nroDoc);
    }

    public PersonalDto findAllByNroDoc(String nroDoc, Pageable pageable) {
        List<PersonalDto> personal = personalRepository.findPersonalAllByNroDoc(nroDoc, pageable);
        if( personal != null && personal.size() > 0){
            return personal.get(0);
        }
        return null;
    }

    public PersonalDto findAllByNroDoc(String nroDoc) {
        List<PersonalDto> personal = personalRepository.findPersonalAllByNroDoc(nroDoc, null);
        if( personal != null && personal.size() > 0){
            return personal.get(0);
        }
        return null;
    }

}
