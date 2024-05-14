package pe.gob.sunass.marcacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.sunass.marcacion.model.MarcacionHorario;

@Repository
public interface HorarioRepository extends JpaRepository<MarcacionHorario, String> {

}