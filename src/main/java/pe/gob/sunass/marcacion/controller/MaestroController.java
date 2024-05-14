package pe.gob.sunass.marcacion.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.sunass.marcacion.dto.MaestroRestOutDto;
import pe.gob.sunass.marcacion.service.MaestroService;

@RestController
@RequestMapping("/api/maestro")
@CrossOrigin
public class MaestroController {
	
	@Autowired
	private MaestroService maestroService;

    @GetMapping("/generico/{tipo}")
    public List<MaestroRestOutDto> findById(@PathVariable String tipo) {
        return maestroService.listAll(tipo);
    }
}
