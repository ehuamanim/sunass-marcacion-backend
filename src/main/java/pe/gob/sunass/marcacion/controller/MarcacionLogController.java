package pe.gob.sunass.marcacion.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import pe.gob.sunass.marcacion.model.MarcacionLog;
import pe.gob.sunass.marcacion.model.Personal;
import pe.gob.sunass.marcacion.security.service.AuthenticationService;
import pe.gob.sunass.marcacion.service.MarcacionLogService;

@RestController
@RequestMapping("/api/log")
@CrossOrigin
public class MarcacionLogController {
    
    @Autowired
    private MarcacionLogService marcacionLogService;

    @Autowired
	private AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<List<MarcacionLog>> listAll(
                HttpServletRequest request,
                @RequestParam(value = "page", defaultValue = "0") int page,
                @RequestParam(value = "size", defaultValue = "10") int size,
                @RequestParam(value = "sort", defaultValue = "fechaLog") String sort) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        Personal personal = authenticationService.getPersonal();
        List<MarcacionLog> marcacionLogList = marcacionLogService.listByPersonalId( personal.getPersonalId(), pageable );
        return ResponseEntity.ok(marcacionLogList);
    }

    @PostMapping
    public ResponseEntity<MarcacionLog> createMarcacionLog(@RequestBody MarcacionLog marcacionLog) {
        Personal personal = authenticationService.getPersonal();
        marcacionLog.setPersonalId(personal.getPersonalId());
        MarcacionLog savedMarcacionLog = marcacionLogService.save(marcacionLog);
        return ResponseEntity.ok(savedMarcacionLog);
    }
}
