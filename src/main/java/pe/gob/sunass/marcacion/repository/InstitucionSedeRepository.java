package pe.gob.sunass.marcacion.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pe.gob.sunass.marcacion.model.InstitucionSede;

@Repository
public interface InstitucionSedeRepository extends JpaRepository<InstitucionSede, String> {
	
    public InstitucionSede findBySedeId(String sedeId);

}