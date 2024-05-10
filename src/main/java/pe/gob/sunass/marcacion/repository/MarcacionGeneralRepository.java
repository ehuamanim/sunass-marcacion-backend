package pe.gob.sunass.marcacion.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.sunass.marcacion.model.MarcacionGeneral;

@Repository
public interface MarcacionGeneralRepository extends JpaRepository<MarcacionGeneral, Long> {
	
    @Query("SELECT count(m) FROM MarcacionGeneral m WHERE m.persCodigo=:persCodigo")
    public Integer rowsPerDay(@Param("persCodigo") String persCodigo);
    
    public MarcacionGeneral findByItem(Long item);

    public List<MarcacionGeneral> findByPersCodigo(String persCodigo);

}