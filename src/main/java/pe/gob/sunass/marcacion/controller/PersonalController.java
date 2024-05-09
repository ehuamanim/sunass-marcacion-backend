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

import pe.gob.sunass.marcacion.dto.PersonalDto;
import pe.gob.sunass.marcacion.facade.PersonalFacade;
import pe.gob.sunass.marcacion.httpconnection.restin.AuthRestIn;
import pe.gob.sunass.marcacion.model.Personal;
import pe.gob.sunass.marcacion.service.PersonalService;

@RestController
@RequestMapping("/api/personal")
@CrossOrigin
public class PersonalController {
    @Autowired
    private PersonalService personalService;

    @Autowired
    private PersonalFacade personalFacade;

    @GetMapping
    public ResponseEntity<List<PersonalDto>> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size,
                                                   @RequestParam(value = "sort", defaultValue = "trabajador") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        List<PersonalDto> personalList = personalService.listAll(pageable);
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

    @PostMapping("/logout")
    public PersonalDto logout(@RequestBody AuthRestIn auth) {
        PersonalDto personal = personalService.findAllByNroDoc( auth.getUsuario() );
        personalFacade.endSesion(personal);
        return personal;
    }
}
