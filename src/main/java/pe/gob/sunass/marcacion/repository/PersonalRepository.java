package pe.gob.sunass.marcacion.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
    List<PersonalDto> findPersonalAllFilter(@Param("filter") String filter, Pageable pageable);

}