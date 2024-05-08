package pe.gob.sunass.marcacion.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.sunass.marcacion.model.MarcacionLog;

@Repository
public interface MarcacionLogRepository extends JpaRepository<MarcacionLog, Long> {

    @Query("SELECT m FROM MarcacionLog m WHERE m.personalId = :personalId ORDER BY m.fechaLog DESC")
    public List<MarcacionLog> listByPersonalId(@Param("personalId") String personalId, Pageable pageable);

}