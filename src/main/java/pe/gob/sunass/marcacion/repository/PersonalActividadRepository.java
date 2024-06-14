package pe.gob.sunass.marcacion.repository;

	import java.sql.Date;
	import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Modifying;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.data.repository.query.Param;
	import org.springframework.stereotype.Repository;

	import jakarta.transaction.Transactional;
	import pe.gob.sunass.marcacion.dto.PersonalActividadDto;
	import pe.gob.sunass.marcacion.model.PersonalActividad;

@Repository
public interface PersonalActividadRepository extends JpaRepository<PersonalActividad, Long> {

	@Query(name = "findActividadesAsignadas", nativeQuery = true)
    List<PersonalActividadDto> findPersonalActividadByNroDoc(@Param("personalId") String nroDoc);

	
	@Modifying
    @Transactional
    @Query(name="updateActividadAsignada", nativeQuery = true)
    void updatePersonalActividad(@Param("item") Long item,
                                 @Param("actividadId") String actividadId,
                                 @Param("personalId") String personalId,
                                 @Param("fechaInicio") Date fechaInicio,
                                 @Param("fechaFin") Date fechaFin,
                                 @Param("status") String status,
                                 @Param("flag") String flag,
                                 @Param("userReg") String userReg,
                                 @Param("fecReg") Date fecReg,
                                 @Param("modalidadId") String modalidadId,
                                 @Param("condicionesId") String condicionesId,
                                 @Param("userMod") String userMod,
                                 @Param("fecMod") Date fecMod);
	
}