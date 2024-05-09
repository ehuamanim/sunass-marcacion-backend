package pe.gob.sunass.marcacion.tempus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.sunass.marcacion.tempus.service.TempusMarcacionService;

@RestController
@RequestMapping("/api/tempusss")
@CrossOrigin
public class TempusControllerAA {

    @Autowired
    private TempusMarcacionService tempusMarcacionService;

    @GetMapping
    public ResponseEntity<List<Object[]>> listAll(){
        List<Object[]> listado = tempusMarcacionService.llamarStoredProcedure("08023186", "2024-04-01", "2024-04-30");
        return ResponseEntity.ok(listado);
    }

}
