package pe.gob.sunass.marcacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.gob.sunass.marcacion.model.PersonalRemoto;
import java.util.List;
import java.util.Date;


@Repository
public interface PersonalRemotoRepository extends JpaRepository<PersonalRemoto, Long> {
    
    @Query("SELECT pr FROM PersonalRemoto pr WHERE pr.personalId=:personalId AND pr.fechaIniRemoto=:fechaIniRemoto")
    public List<PersonalRemoto> findByPersonalAndFechaIniRemoto(String personalId, Date fechaIniRemoto);

    @Query("SELECT pr FROM PersonalRemoto pr WHERE pr.personalId=:personalId AND TRUNC(pr.fechaIniRemoto) >= TRUNC(:fechaIniRemoto)")
    public List<PersonalRemoto> findByPersonalRemotoDesdeFechaActual(String personalId, Date fechaIniRemoto);

}