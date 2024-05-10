package pe.gob.sunass.marcacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.sunass.marcacion.model.InstitucionSede;

@Repository
public interface InstitucionSedeRepository extends JpaRepository<InstitucionSede, String> {
	
    public InstitucionSede findBySedeId(String sedeId);

}