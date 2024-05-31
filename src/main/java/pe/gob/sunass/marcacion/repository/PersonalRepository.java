package pe.gob.sunass.marcacion.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.sunass.marcacion.dto.PersonalDto;
import pe.gob.sunass.marcacion.model.Personal;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, String> {

    public Personal findByPersonalId(String personalId);

    public Personal findByNroDoc(String nroDoc);

    public Personal findByUsername(String username);

    @Query(name = "findPersonalAllByNroDoc", nativeQuery = true)
    List<PersonalDto> findPersonalAllByNroDoc(@Param("nroDoc") String nroDoc, Pageable pageable);

    @Query(name = "findPersonalAll", nativeQuery = true)
    List<PersonalDto> findPersonalAllFilter(@Param("filter") String filter, @Param("unidadOrganizativa") String unidadOrganizativa, Pageable pageable);
    
    @Modifying
    @Query(name = "updatePersonal", nativeQuery = true)
    void updatePersonal(@Param("personalId") String personalId,
                        @Param("planillaId") String planillaId,
                        @Param("nroDoc") String nroDoc,
                        @Param("trabajador") String trabajador,
                        @Param("flag") String flag,
                        @Param("situacionEspId") String situacionEspId,
                        @Param("cargoId") String cargoId,
                        @Param("tipoPersonaId") String tipoPersonaId,
                        @Param("itemUo") String itemUo,
                        @Param("tipoDocId") String tipoDocId,
                        @Param("condicionId") String condicionId,
                        @Param("fechaIngreso") Date fechaIngreso,
                        @Param("fechaCese") Date fechaCese,
                        @Param("username") String username,
                        @Param("rol") String rol,
                        @Param("macAddress") String macAddress,
					    @Param("macAddress2") String macAddress2,
					    @Param("flagMacAddress") String flagMacAddress);
}