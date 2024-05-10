package pe.gob.sunass.marcacion.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pe.gob.sunass.marcacion.constant.AppConstant;
import pe.gob.sunass.marcacion.dto.PersonalActividadDto;
import pe.gob.sunass.marcacion.model.MarcacionGeneral;
import pe.gob.sunass.marcacion.repository.MarcacionGeneralRepository;
import pe.gob.sunass.marcacion.repository.PersonalActividadRepository;

@Transactional
@Service
public class MarcacionGeneralService {

    @Autowired
    private MarcacionGeneralRepository marcacionGeneralRepository;
    
    @Autowired
    private PersonalActividadRepository personalActividadRepository;

    public MarcacionGeneral save(MarcacionGeneral marcacionGeneral) {
        return marcacionGeneralRepository.save(marcacionGeneral);
    }

    public MarcacionGeneral findByItem( Long item ){
        return marcacionGeneralRepository.findByItem( item );
    }

    public List<MarcacionGeneral> findByPersCodigo( String persCodigo ){
        return marcacionGeneralRepository.findByPersCodigo( persCodigo );
    }

    
    public List<MarcacionGeneral> findAllByPersCodigo( String persCodigo ){
    	
    	// Listando todas las actividades del usuario
    	List<MarcacionGeneral> marcacion = marcacionGeneralRepository.findByPersCodigo( persCodigo );
    	List<MarcacionGeneral> marcacionAsignado = marcacion
							    	.stream()
							    	.filter( m -> m.getEsAsignado() == AppConstant.EST_ASIGNADO )
							    	.collect( Collectors.toList() );
    	int actAsignadas = marcacionAsignado.size();
    	
    	// Actividades asignadas por el jefe
    	List<PersonalActividadDto> personaActividad = personalActividadRepository.findPersonalActividadByNroDoc( persCodigo );
    	int actividadesAsignadasSinVer = personaActividad.size();
    	
    	if( actAsignadas != actividadesAsignadasSinVer ) {
    		List< MarcacionGeneral > marcacionPorGuardar = filtrarActividades( marcacionAsignado, personaActividad );
    		guardarMarcaciones(marcacionPorGuardar);
    		marcacion.addAll(marcacionPorGuardar);
    	}
    	
    	return marcacion;
    }
    
    private List<MarcacionGeneral> filtrarActividades( List<MarcacionGeneral> marcacion, List<PersonalActividadDto> personaActividad ) {
    	
    	List<MarcacionGeneral> result = new ArrayList<MarcacionGeneral>();
    	
    	if( marcacion.size() == 0 ) {
    		result = generarMarcaciones( personaActividad ); 
    		return result;
    	}
    	
    	List<PersonalActividadDto> personalList = personaActividad
	    	.stream()
	    	.filter( pa -> {
	    		Long count = marcacion
		    				.stream()
		    				.filter( m -> m.getPersonalActividadId().compareTo(pa.getItem()) == 0 )
		    				.count();
	    		return count.intValue() > 0; 
	    	})
	    	.collect(Collectors.toList());
    	
    	if( personalList != null ) {
    		result = generarMarcaciones( personalList );
    	}
    	
    	return result;
    }
    
    private List<MarcacionGeneral> generarMarcaciones( List<PersonalActividadDto> personalList ) {
    	return personalList
	    	.stream()
	    	.map( pl -> {
	    		MarcacionGeneral m = new MarcacionGeneral();
	    		m.setPersCodigo( pl.getPersonalId() );
	    		m.setDescripcion( pl.getActividad() );
	    		m.setFechaLog( new Date() );
	    		m.setFechaReg( new Date() );
	    		m.setFlagAtendido( AppConstant.FLAG_SIN_INICIAR );
	    		m.setEstAtenId( AppConstant.EST_ATENCION_PENDIENTE );
	    		m.setNroDoc( pl.getNroDoc() );
	    		m.setEsAsignado( AppConstant.EST_ASIGNADO );
	    		m.setPersonalActividadId( pl.getItem() );
	    		m.setOrdenVisual( AppConstant.ORDEN_VISUAL_ACTIVIDAD_ASIGNADAS );
	    		return m;
	    	})
	    	.collect( Collectors.toList() );
    }
    
    private void guardarMarcaciones( List<MarcacionGeneral> marcaciones ){
    	marcaciones
    	.stream()
    	.forEach( m -> {
    		save(m);
    	});
    }
}
