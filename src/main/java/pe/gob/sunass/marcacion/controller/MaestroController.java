package pe.gob.sunass.marcacion.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.sunass.marcacion.constant.AppConstant;
import pe.gob.sunass.marcacion.dto.MaestroRestOutDto;
import pe.gob.sunass.marcacion.service.ActividadService;

@RestController
@RequestMapping("/api/maestro")
@CrossOrigin
public class MaestroController {
	@Autowired
    private ActividadService actividadService;

    @GetMapping("/generico/{tipo}")
    public List<MaestroRestOutDto> findById(@PathVariable String tipo) {
    	
    	List<MaestroRestOutDto> maestro = new ArrayList<MaestroRestOutDto>();
    	if( tipo.equals( AppConstant.MAESTRO_ACTIVIDADES ) ) {
    		maestro = actividadService
    				.listAll()
    				.stream()
    				.map( a -> new MaestroRestOutDto(a.getActividadId(), a.getDescripcion(), a.getFlag()) )
    				.collect( Collectors.toList() );
    	}
    	
        return maestro;
    }
}
