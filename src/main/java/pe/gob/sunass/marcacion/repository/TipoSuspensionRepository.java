package pe.gob.sunass.marcacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.sunass.marcacion.model.MarcacionTipoSuspension;

@Repository
public interface TipoSuspensionRepository extends JpaRepository<MarcacionTipoSuspension, String> {

}