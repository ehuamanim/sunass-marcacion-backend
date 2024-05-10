package pe.gob.sunass.marcacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.sunass.marcacion.model.Ejercicio;
import pe.gob.sunass.marcacion.model.MarcacionTipoDia;
import pe.gob.sunass.marcacion.model.MotivoNoCumplimiento;
import pe.gob.sunass.marcacion.model.PersonalCargo;
import pe.gob.sunass.marcacion.model.PersonalCondicion;
import pe.gob.sunass.marcacion.model.PersonalRemoto;
import pe.gob.sunass.marcacion.model.PersonalSituacion;
import pe.gob.sunass.marcacion.model.PersonalTipoDoc;
import pe.gob.sunass.marcacion.model.PersonalTipoTrabajador;
import pe.gob.sunass.marcacion.model.PersonalUo;

@Repository
public interface TipoSuspensionRepository extends JpaRepository<MarcacionTipoDia, String> {

}