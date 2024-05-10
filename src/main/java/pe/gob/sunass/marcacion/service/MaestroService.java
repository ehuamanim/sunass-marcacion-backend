package pe.gob.sunass.marcacion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.sunass.marcacion.constant.AppConstant;
import pe.gob.sunass.marcacion.dto.MaestroRestOutDto;
import pe.gob.sunass.marcacion.repository.MotivoNoCumplimientoRepository;

@Service
public class MaestroService {

	@Autowired
    private ActividadService actividadService;
	
	@Autowired
	private MotivoNoCumplimientoRepository motivoNoCumplimientoRepository;
	
	public List<MaestroRestOutDto> listAll( String tipo ){
		
		List<MaestroRestOutDto> maestro = new ArrayList<MaestroRestOutDto>();
		
    	if( tipo.equals( AppConstant.MAESTRO_ACTIVIDADES ) ) {
    		maestro = actividadService
    				.listAll()
    				.stream()
    				.map( a -> new MaestroRestOutDto(a.getActividadId(), a.getDescripcion(), a.getFlag()) )
    				.collect( Collectors.toList() );
    	}
    	
    	if( tipo.equals( AppConstant.MAESTRO_MOTIVO_NO_C ) ) {
    		maestro = motivoNoCumplimientoRepository
    				.findAll()
    				.stream()
    				.map( a -> new MaestroRestOutDto(a.getMotivoId(), a.getDescripcion(), a.getFlag()) )
    				.collect( Collectors.toList() );
    	}
    	
    	return maestro;
	}
	
}
