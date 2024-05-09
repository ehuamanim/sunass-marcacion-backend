package pe.gob.sunass.marcacion.tempus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.gob.sunass.marcacion.tempus.model.AutorizacionAcceso;

@Repository
public interface TempusMarcacionRepository extends CrudRepository<AutorizacionAcceso, Long> {

    @Procedure(name = "usp_info_marcacion")
    List<Object[]> llamarStoredProcedure(String nrodoc, String fecha_ini, String fecha_fin);

}