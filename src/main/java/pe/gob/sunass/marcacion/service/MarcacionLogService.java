package pe.gob.sunass.marcacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.gob.sunass.marcacion.model.MarcacionLog;
import pe.gob.sunass.marcacion.repository.MarcacionLogRepository;

@Service
public class MarcacionLogService {

    @Autowired
    private MarcacionLogRepository marcacionLogRepository;

    public List<MarcacionLog> listByPersonalId(String idPersonal, Pageable pageable) {
        return marcacionLogRepository.listByPersonalId(idPersonal, pageable);
    }

    public MarcacionLog save(MarcacionLog marcacionLog) {
        return marcacionLog;
    }

}
