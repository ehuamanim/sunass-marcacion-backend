package pe.gob.sunass.marcacion.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.sunass.marcacion.dto.AsignacionPersonalRestIn;
import pe.gob.sunass.marcacion.dto.BaseResponseDto;
import pe.gob.sunass.marcacion.dto.FilterRestInRO;
import pe.gob.sunass.marcacion.dto.PersonalActividadDto;
import pe.gob.sunass.marcacion.dto.PersonalDto;
import pe.gob.sunass.marcacion.facade.PersonalFacade;
import pe.gob.sunass.marcacion.httpconnection.restin.AuthRestIn;
import pe.gob.sunass.marcacion.model.Personal;
import pe.gob.sunass.marcacion.model.PersonalActividad;
import pe.gob.sunass.marcacion.repository.PersonalActividadRepository;
import pe.gob.sunass.marcacion.service.PersonalService;

@RestController
@RequestMapping("/api/personal")
@CrossOrigin
public class PersonalController {
    
    @Autowired
    private PersonalService personalService;

    @Autowired
    private PersonalFacade personalFacade;
    
    @PostMapping
    public ResponseEntity<List<PersonalDto>> listAll(
                        @RequestBody FilterRestInRO filter,
                        @RequestParam(value = "unidadOrganizativa", required = false) String unidadOrganizativa,
                        @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "size", defaultValue = "10") int size,
                        @RequestParam(value = "sort", defaultValue = "trabajador") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        List<PersonalDto> personalList = personalService.listAllFilter(filter.getFilter(), unidadOrganizativa, pageable);
        return ResponseEntity.ok(personalList);
    }

    @GetMapping("/{personalId}")
    public Personal findById(@PathVariable String personalId) {
        return personalService.findById(personalId);
    }

    @GetMapping("/nrodoc/{nroDoc}")
    public Personal findNroDoc(@PathVariable String nroDoc) {
        return personalService.findByNroDoc(nroDoc);
    }

    @PostMapping("/auth")
    public PersonalDto login(@RequestBody AuthRestIn auth) {
        PersonalDto personal = personalService.findAllByNroDoc( auth.getUsuario() );
        personalFacade.startSesion(personal);
        return personal;
    }

    @PostMapping("/asignacion")
    public BaseResponseDto asignacion(@RequestBody  AsignacionPersonalRestIn asignacion) {
        BaseResponseDto resp = personalService.guardarAsignacion( asignacion );
        return resp;
    }
    
    @PutMapping("/update")
    public ResponseEntity<BaseResponseDto> updatePersonal(@RequestBody PersonalDto personalDto) {
        try {
            personalService.updatePersonal(personalDto);
            BaseResponseDto response = new BaseResponseDto("200", "Actualización exitosa");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            BaseResponseDto response = new BaseResponseDto("400", "Error al actualizar el personal: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/asignaciones/{personalId}")
    public ResponseEntity<List<PersonalActividadDto>> getAsignacionesByPersonalId(@PathVariable String personalId) {
        try {
            List<PersonalActividadDto> asignaciones = personalService.getAssignmentsByPersonalId(personalId);
            return ResponseEntity.ok(asignaciones);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/asignaciones/{personalId}/{assignmentId}")
    public ResponseEntity<BaseResponseDto> updateAsignacion(
            @PathVariable String personalId,
            @PathVariable Long assignmentId,
            @RequestBody PersonalActividad asignacionDto) {
        try {
            asignacionDto.setPersonalId(personalId); // Asignamos el personal_id al objeto si no viene en el body
            asignacionDto.setItem(assignmentId); // Asignamos el assignmentId al objeto
            BaseResponseDto response = personalService.updateAssignment(assignmentId, asignacionDto); // Llamada al servicio para actualizar la asignación
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            BaseResponseDto response = new BaseResponseDto("400", "Error al actualizar la asignación: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}
