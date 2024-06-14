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

    @Query("SELECT pr FROM PersonalRemoto pr WHERE pr.personalId = :personalId AND TO_CHAR(pr.fechaIniRemoto, 'YYYY-MM-DD') >= TO_CHAR(:fechaIniRemoto, 'YYYY-MM-DD')")
    public List<PersonalRemoto> findByPersonalRemotoDesdeFechaActual(String personalId, Date fechaIniRemoto);


}