package pe.gob.sunass.marcacion.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.sunass.marcacion.constant.AppConstant;
import pe.gob.sunass.marcacion.dto.PersonalDto;
import pe.gob.sunass.marcacion.facade.PersonalFacade;
import pe.gob.sunass.marcacion.httpconnection.restin.AuthRestIn;
import pe.gob.sunass.marcacion.model.MarcacionGeneral;
import pe.gob.sunass.marcacion.service.MarcacionGeneralService;
import pe.gob.sunass.marcacion.service.PersonalService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/marcacion")
@CrossOrigin
public class MarcacionGeneralController {
    
    @Autowired
    private MarcacionGeneralService marcacionGeneralService;

    @Autowired
    private PersonalService personalService;

    @Autowired
    private PersonalFacade personalFacade;

    @PostMapping
    public ResponseEntity<MarcacionGeneral> createMarcacion(@RequestBody MarcacionGeneral marcacionGeneral) {
        marcacionGeneral.setFechaIni(new Date());
        marcacionGeneral.setFechaReg(new Date());
        marcacionGeneral.setFechaLog(new Date());
        marcacionGeneral.setFlagAtendido( AppConstant.FLAG_ATENDIDO_PENDIENTE );
        MarcacionGeneral savedMarcacionGeneral = marcacionGeneralService.save(marcacionGeneral);
        return ResponseEntity.ok(savedMarcacionGeneral);
    }

    @PutMapping("/finalizar")
    public ResponseEntity<MarcacionGeneral> finalizarMarcacion(@RequestBody MarcacionGeneral marcacionGeneral) {
        MarcacionGeneral savedMarcacionGeneral = marcacionGeneralService.findByItem( marcacionGeneral.getItem() );
        savedMarcacionGeneral.setFechaFin(new Date());
        savedMarcacionGeneral.setObservacion( marcacionGeneral.getObservacion() );
        savedMarcacionGeneral.setFlagAtendido( AppConstant.FLAG_ATENDIDO_FALTANTE );
        marcacionGeneralService.save(savedMarcacionGeneral);
        return ResponseEntity.ok(savedMarcacionGeneral);
    }

    @PostMapping("/endactivity")
    public MarcacionGeneral login(@RequestBody AuthRestIn auth) {
        PersonalDto personal = personalService.findAllByNroDoc( auth.getUsuario() );
        personalFacade.endActivity(personal);
        return personalFacade.getMarcacionGenerada();
    }

    @GetMapping("per/{personaId}")
    public ResponseEntity<List<MarcacionGeneral>> listByPers(@PathVariable String personaId) {
        List<MarcacionGeneral> marcaciones = 
            marcacionGeneralService.findByPersCodigo(personaId)
                .stream()
                .sorted( ( pre, post ) -> pre.getFechaIni().compareTo( post.getFechaIni() ) )
                .collect(Collectors.toList());
        return ResponseEntity.ok( marcaciones );
    }
    
}
