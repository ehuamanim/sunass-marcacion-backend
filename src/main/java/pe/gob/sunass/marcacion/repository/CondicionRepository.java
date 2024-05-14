package pe.gob.sunass.marcacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.sunass.marcacion.model.PersonalCondicion;

@Repository
public interface CondicionRepository extends JpaRepository<PersonalCondicion, String> {

}