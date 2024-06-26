package pe.gob.sunass.marcacion.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pe.gob.sunass.marcacion.common.FechaUtil;
import pe.gob.sunass.marcacion.dto.AsignacionPersonalRestIn;
import pe.gob.sunass.marcacion.dto.BaseResponseDto;
import pe.gob.sunass.marcacion.dto.MaestroRestOutDto;
import pe.gob.sunass.marcacion.dto.PersonalActividadDto;
import pe.gob.sunass.marcacion.dto.PersonalDto;
import pe.gob.sunass.marcacion.model.Personal;
import pe.gob.sunass.marcacion.model.PersonalActividad;
import pe.gob.sunass.marcacion.model.PersonalRemoto;
import pe.gob.sunass.marcacion.repository.PersonalActividadRepository;
import pe.gob.sunass.marcacion.repository.PersonalRemotoRepository;
import pe.gob.sunass.marcacion.repository.PersonalRepository;

@Service
@Transactional
public class PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    @Autowired
    private PersonalActividadRepository personalActividadRepository;

    @Autowired
    private PersonalRemotoRepository personalRemotoRepository;

    public List<PersonalDto> listAllFilter(String filter, String unidadOrganizativa,  Pageable pageable) {
        return personalRepository.findPersonalAllFilter("%" + filter + "%", unidadOrganizativa, pageable);
    }

    public Personal findById(String personalId) {
        return personalRepository.findByPersonalId(personalId);
    }

    public Personal findByNroDoc(String nroDoc) {
        return personalRepository.findByNroDoc(nroDoc);
    }

    public PersonalDto findAllByNroDoc(String nroDoc, Pageable pageable) {
        List<PersonalDto> personal = personalRepository.findPersonalAllByNroDoc(nroDoc, pageable);
        if( personal != null && personal.size() > 0){
            return personal.get(0);
        }
        return null;
    }

    public PersonalDto findAllByNroDoc(String nroDoc) {
        List<PersonalDto> personal = personalRepository.findPersonalAllByNroDoc(nroDoc, null);
        if( personal != null && personal.size() > 0){
            return personal.get(0);
        }
        return null;
    }

    public BaseResponseDto guardarAsignacion(AsignacionPersonalRestIn asignacion) {
        BaseResponseDto response = new BaseResponseDto( "200", "Registro exitoso" );
        
        // Guardando actividades
        List<PersonalActividad> paList =  new ArrayList<>();
        generarAsignacion(paList, asignacion.getPersonal(), asignacion.getActividades(), asignacion.getDias(), asignacion.getModalidad(), asignacion.getCondiciones());
        for (PersonalActividad pa : paList) {
            personalActividadRepository.save( pa );
        }
        
        // Guardando accesos remotos
        List<PersonalRemoto> prList =  new ArrayList<>();
        generarPersonalRemoto( prList, asignacion.getPersonal(), asignacion.getDias() );
        for (PersonalRemoto pr : prList) {
            personalRemotoRepository.save( pr );
        }

        return response;
    }
    
    public void updatePersonal(PersonalDto personalDto) {
        personalRepository.updatePersonal(
            personalDto.getPersonalId(),
            personalDto.getPlanillaId(),
            personalDto.getNroDoc(),
            personalDto.getTrabajador(),
            personalDto.getFlag(),
            personalDto.getSituacionEspId(),
            personalDto.getCargoId(),
            personalDto.getTipoPersonaId(),
            personalDto.getItemUo(),
            personalDto.getTipoDocId(),
            personalDto.getCondicionId(),
            personalDto.getFechaIngreso(),
            personalDto.getFechaCese(),
            personalDto.getUsername(),
            personalDto.getRol(),
            personalDto.getMacAddress(),
            personalDto.getMacAddress2(),
            personalDto.getFlagMacAddress()
        );
    }

    public BaseResponseDto listAccesosRemotoPersonal(String personalId) {
        BaseResponseDto response = new BaseResponseDto( "200", "Listado de personal" );
        
        // Guardando accesos remotos
        personalRemotoRepository.findByPersonalAndFechaIniRemoto( personalId, new Date());

        return response;
    }

    private void generarPersonalRemoto( List<PersonalRemoto> prList, List<PersonalDto> personal, List<Date> fechas){
        for( PersonalDto p : personal ){
            agregarPersonalRemoto(prList, p, fechas);
        }
    }

    private void agregarPersonalRemoto( List<PersonalRemoto> prList, PersonalDto personal, List<Date> fechas){
        for( Date d : fechas ){
            PersonalRemoto pr = new PersonalRemoto();
            Date fechaInicio = FechaUtil.getDateWithoutHours(d);
            Date fechaFin = FechaUtil.addTime(fechaInicio, 23, 59);

            pr.setPersonalId( personal.getPersonalId() );
            pr.setFechaIniRemoto(fechaInicio);
            pr.setFechaFinRemoto(fechaFin);
            pr.setStatus( "P" );
            pr.setFlag( "1" );
            pr.setUserReg( "admin" );
            pr.setFecReg(new Date());
            prList.add(pr);
        }
    }

    private void generarAsignacion( List<PersonalActividad> paList, List<PersonalDto> personal, List<MaestroRestOutDto> actividades, List<Date> fechas, String modalidad, String condiciones){
        for( PersonalDto p : personal ){
            agregarAsignacion(paList, p, actividades, fechas, modalidad, condiciones);
        }
    }

    private void agregarAsignacion( List<PersonalActividad> paList, PersonalDto personal, List<MaestroRestOutDto> actividades, List<Date> fechas, String modalidad, String condiciones ){
        for( MaestroRestOutDto m: actividades ){
          agregarAsignacion(paList, personal, m, fechas, modalidad, condiciones);  
        }
    }

    private void agregarAsignacion( List<PersonalActividad> paList, PersonalDto personal, MaestroRestOutDto actividad, List<Date> fechas, String modalidad, String condiciones ){
        for( Date f: fechas ){
            Date fechaInicio = FechaUtil.getDateWithoutHours(f);
            Date fechaFin = FechaUtil.addTime(fechaInicio, 23, 59);
            PersonalActividad pa = new PersonalActividad();

            pa.setActividadId(actividad.getId());
            pa.setPersonalId( personal.getPersonalId() );
            pa.setFechaInicio( fechaInicio );
            pa.setFechaFin( fechaFin );
            pa.setStatus( "P" );
            pa.setFlag( "1" );
            pa.setUserReg( "admin" );
            pa.setFecReg(new Date());
            pa.setModalidadId(modalidad);
            pa.setCondicionesId(condiciones); 
            paList.add(pa);
        }
    }
    
 // Nueva función: Obtener lista de asignaciones según personal_id
    public List<PersonalActividadDto> getAssignmentsByPersonalId(String personalId) {
        return personalActividadRepository.findPersonalActividadByNroDoc(personalId);
    }

 // Nueva función: Actualizar una asignación
 // Nueva función: Actualizar una asignación
    public BaseResponseDto updateAssignment(Long assignmentId, PersonalActividad updatedAssignment) {
        BaseResponseDto response = new BaseResponseDto("200", "Actualización exitosa");
        try {
            PersonalActividad existingAssignment = personalActividadRepository.findById(assignmentId)
                    .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

            // Verificar y actualizar los campos que han cambiado en updatedAssignment
            if (updatedAssignment.getActividadId() != null) {
                existingAssignment.setActividadId(updatedAssignment.getActividadId());
            }
            if (updatedAssignment.getPersonalId() != null) {
                existingAssignment.setPersonalId(updatedAssignment.getPersonalId());
            }
            if (updatedAssignment.getFechaInicio() != null) {
                existingAssignment.setFechaInicio(updatedAssignment.getFechaInicio());
            }
            if (updatedAssignment.getFechaFin() != null) {
                existingAssignment.setFechaFin(updatedAssignment.getFechaFin());
            }
            if (updatedAssignment.getStatus() != null) {
                existingAssignment.setStatus(updatedAssignment.getStatus());
            }
            if (updatedAssignment.getFlag() != null) {
                existingAssignment.setFlag(updatedAssignment.getFlag());
            }
            if (updatedAssignment.getUserReg() != null) {
                existingAssignment.setUserReg(updatedAssignment.getUserReg());
            }
            if (updatedAssignment.getFecReg() != null) {
                existingAssignment.setFecReg(updatedAssignment.getFecReg());
            }
            if (updatedAssignment.getModalidadId() != null) {
                existingAssignment.setModalidadId(updatedAssignment.getModalidadId());
            }
            if (updatedAssignment.getCondicionesId() != null) {
                existingAssignment.setCondicionesId(updatedAssignment.getCondicionesId());
            }
            if (updatedAssignment.getUserMod() != null) {
                existingAssignment.setUserMod(updatedAssignment.getUserMod());
            }
            existingAssignment.setFecMod(new Date()); // Actualizar siempre la fecha de modificación

            personalActividadRepository.save(existingAssignment);
        } catch (Exception e) {
            response = new BaseResponseDto("500", "Error en la actualización: " + e.getMessage());
        }
        return response;
    }


}
