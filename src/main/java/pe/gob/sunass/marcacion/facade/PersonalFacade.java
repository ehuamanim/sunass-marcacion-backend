package pe.gob.sunass.marcacion.facade;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import pe.gob.sunass.marcacion.constant.AppConstant;
import pe.gob.sunass.marcacion.constant.PropertiesConstant;
import pe.gob.sunass.marcacion.dto.PersonalDto;
import pe.gob.sunass.marcacion.model.MarcacionGeneral;
import pe.gob.sunass.marcacion.model.MarcacionLog;
import pe.gob.sunass.marcacion.repository.MarcacionGeneralRepository;
import pe.gob.sunass.marcacion.repository.MarcacionLogRepository;

@Component
public class PersonalFacade {

    @Autowired
    private PropertiesConstant props;

    @Autowired
    private MarcacionGeneralRepository marcacionGeneralRepository;

    @Autowired
    private MarcacionLogRepository marcacionLogRepository;

    @Getter
    private MarcacionGeneral marcacionGenerada;

    public void startSesion( PersonalDto personal ){
        if( personal == null ){
            return;
        }
        Integer rows = marcacionGeneralRepository.rowsPerDay( personal.getPersonalId() );
        if( rows == 0 ){
            crearMarcacionGeneral( personal, props.getMensajeLogin(), AppConstant.ORDEN_VISUAL_INICIO_SESION );
        }
        crearMarcacionLog(personal, props.getMensajeLogin());
    }

    public void endSesion( PersonalDto personal ){
        if( personal == null ){
            return;
        }
        crearMarcacionLog(personal, props.getMensajeLogout());
    }

    public void endActivity( PersonalDto personal ){
        if( personal == null ){
            return;
        }
        crearMarcacionGeneral( personal, props.getMensajeEndWork(), AppConstant.ORDEN_VISUAL_FINALIZAR_DIA );
        crearMarcacionLog(personal, props.getMensajeEndWork());
    }

    private void crearMarcacionGeneral( PersonalDto personal, String message, Integer ordenVisual ){
        marcacionGenerada = new MarcacionGeneral();
        marcacionGenerada.setDescripcion( message );
        marcacionGenerada.setObservacion( message );
        marcacionGenerada.setNroDoc( personal.getNroDoc() );
        marcacionGenerada.setFechaIni( new Date() );
        marcacionGenerada.setFechaFin( new Date() );
        marcacionGenerada.setFechaLog( new Date() );
        marcacionGenerada.setFechaReg( new Date() );
        marcacionGenerada.setPersCodigo( personal.getPersonalId() );
        marcacionGenerada.setEstAtenId( AppConstant.EST_ATENCION_RESUELTO );
        marcacionGenerada.setFlagAtendido( AppConstant.FLAG_ATENDIDO_REALIZADO );
        marcacionGenerada.setOrdenVisual( ordenVisual );
        marcacionGenerada.setFlag( AppConstant.FLAG_ACTIVO );
        marcacionGeneralRepository.save( marcacionGenerada );
    }

    private void crearMarcacionLog( PersonalDto personal, String message ){
        MarcacionLog marcacionLog = new MarcacionLog();
        marcacionLog.setPersonalId( personal.getPersonalId() );
        marcacionLog.setFechaLog( new Date() );
        marcacionLog.setDescripcion( message );
        marcacionLogRepository.save(marcacionLog);
    }

}
