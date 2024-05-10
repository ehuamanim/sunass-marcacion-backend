package pe.gob.sunass.marcacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.sunass.marcacion.dto.PersonalActividadDto;
import pe.gob.sunass.marcacion.model.PersonalActividad;

@Repository
public interface PersonalActividadRepository extends JpaRepository<PersonalActividad, Long> {

	@Query(name = "findActividadesAsignadas", nativeQuery = true)
    List<PersonalActividadDto> findPersonalActividadByNroDoc(@Param("personaId") String nroDoc);
	
}