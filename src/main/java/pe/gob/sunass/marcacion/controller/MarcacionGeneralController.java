package pe.gob.sunass.marcacion.controller;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.gob.sunass.marcacion.apirest.dto.AlfrescoFileRestOutRO;
import pe.gob.sunass.marcacion.comparator.MarcacionGeneralComparator;
import pe.gob.sunass.marcacion.constant.AppConstant;
import pe.gob.sunass.marcacion.constant.PropertiesConstant;
import pe.gob.sunass.marcacion.dto.PersonalDto;
import pe.gob.sunass.marcacion.facade.PersonalFacade;
import pe.gob.sunass.marcacion.model.MarcacionGeneral;
import pe.gob.sunass.marcacion.model.Personal;
import pe.gob.sunass.marcacion.security.service.AuthenticationService;
import pe.gob.sunass.marcacion.service.AlfrescoService;
import pe.gob.sunass.marcacion.service.MarcacionGeneralService;
import pe.gob.sunass.marcacion.service.PersonalService;

@RestController
@RequestMapping("/api/marcacion")
@CrossOrigin
public class MarcacionGeneralController {

    private static final LocalDate FUTURE_DATE = LocalDate.of(9999, 12, 31);

    @Autowired
    private MarcacionGeneralService marcacionGeneralService;

    @Autowired
    private PersonalService personalService;

    @Autowired
    private PersonalFacade personalFacade;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AlfrescoService alfrescoService;

    @PostMapping
    public ResponseEntity<MarcacionGeneral> createMarcacion(@RequestBody MarcacionGeneral marcacionGeneral) {

        Personal p = authenticationService.getPersonal();
        marcacionGeneral.setPersCodigo(p.getPersonalId());
        marcacionGeneral.setPersUsuario(p.getUsername());
        marcacionGeneral.setFechaReg(new Date());
        marcacionGeneral.setFechaLog(new Date());
        marcacionGeneral.setFlagAtendido(AppConstant.FLAG_SIN_INICIAR);
        marcacionGeneral.setOrdenVisual(AppConstant.ORDEN_VISUAL_ACTIVIDAD_MANUAL);
        marcacionGeneral.setFlag(AppConstant.FLAG_ACTIVO);
        MarcacionGeneral savedMarcacionGeneral = marcacionGeneralService.save(marcacionGeneral);
        return ResponseEntity.ok(savedMarcacionGeneral);
    }

    @PutMapping
    public ResponseEntity<MarcacionGeneral> actualizarMarcacion(@RequestBody MarcacionGeneral marcacionGeneral) {
        MarcacionGeneral savedMarcacionGeneral = marcacionGeneralService.findByItem(marcacionGeneral.getItem());
        savedMarcacionGeneral.setDescripcion(marcacionGeneral.getDescripcion());
        marcacionGeneralService.save(savedMarcacionGeneral);
        return ResponseEntity.ok(savedMarcacionGeneral);
    }

    @PutMapping("/iniciar")
    public ResponseEntity<MarcacionGeneral> iniciarMarcacion(@RequestBody MarcacionGeneral marcacionGeneral) {
        MarcacionGeneral savedMarcacionGeneral = marcacionGeneralService.findByItem(marcacionGeneral.getItem());
        savedMarcacionGeneral.setFechaIni(new Date());
        savedMarcacionGeneral.setFlagAtendido(AppConstant.FLAG_ATENDIDO_PENDIENTE);
        marcacionGeneralService.save(savedMarcacionGeneral);
        return ResponseEntity.ok(savedMarcacionGeneral);
    }

    @PutMapping("/finalizar")
    public ResponseEntity<MarcacionGeneral> finalizarMarcacion(@RequestBody MarcacionGeneral marcacionGeneral) {
    	System.out.println("============================================");
    	System.out.println(marcacionGeneral);
    	System.out.println("============================================");
        MarcacionGeneral savedMarcacionGeneral = marcacionGeneralService.findByItem(marcacionGeneral.getItem());
        savedMarcacionGeneral.setFechaFin(new Date());
        savedMarcacionGeneral.setAlfrescoId( marcacionGeneral.getAlfrescoId() );
        savedMarcacionGeneral.setNombreArchivo( marcacionGeneral.getNombreArchivo() );
        savedMarcacionGeneral.setObservacion(marcacionGeneral.getObservacion());
        savedMarcacionGeneral.setTareaFinalizada(marcacionGeneral.getTareaFinalizada());
        savedMarcacionGeneral.setFlagAtendido(AppConstant.FLAG_ATENDIDO_REALIZADO);
        savedMarcacionGeneral.setEstAtenId(AppConstant.EST_ATENCION_RESUELTO);
        marcacionGeneralService.save(savedMarcacionGeneral);
        return ResponseEntity.ok(savedMarcacionGeneral);
    }

    @GetMapping("/endactivity")
    public MarcacionGeneral login() {
        Personal p = authenticationService.getPersonal();
        PersonalDto personal = personalService.findAllByNroDoc(p.getNroDoc());
        personalFacade.endActivity(personal);
        return personalFacade.getMarcacionGenerada();
    }

    @GetMapping("per")
    public ResponseEntity<List<MarcacionGeneral>> listByPers() {

        // Obteniendo las marcaciones
        Personal personal = authenticationService.getPersonal();
        List<MarcacionGeneral> marcaciones = marcacionGeneralService.findAllByPersCodigo(personal.getPersonalId());
        marcaciones.sort(Comparator
                .comparingInt(MarcacionGeneral::getOrdenVisual)
                .thenComparing(MarcacionGeneralComparator.nullsLastComparator(Comparator.naturalOrder(),
                        MarcacionGeneral::getFechaIni))
                .thenComparing(e -> FUTURE_DATE));

        marcaciones = marcaciones
                .stream()
                .filter(m -> m.getFlag().equals(AppConstant.FLAG_ACTIVO))
                .collect(Collectors.toList());

        return ResponseEntity.ok(marcaciones);
    }

    @DeleteMapping("{marcacionId}")
    public ResponseEntity<MarcacionGeneral> delete(@PathVariable Long marcacionId) {
        MarcacionGeneral marcacion = marcacionGeneralService.findByItem(marcacionId);
        marcacion.setFlag(AppConstant.FLAG_ELIMINADO);
        marcacionGeneralService.save(marcacion);
        return ResponseEntity.ok(marcacion);
    }

    @PostMapping("/upload")
    public ResponseEntity<AlfrescoFileRestOutRO> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("folderPath") String folderPath) {
    	
    	AlfrescoFileRestOutRO uploadFile = new AlfrescoFileRestOutRO();
    	uploadFile.setStatus("ERROR");
    	uploadFile.setMessage("No se encuentra archivo");
    	
        if (file.isEmpty()) {
            return new ResponseEntity<>(uploadFile, HttpStatus.BAD_REQUEST);
        }

        try {
            Personal p = authenticationService.getPersonal();
            String filename = file.getOriginalFilename();
            AlfrescoFileRestOutRO uploadFlie =  alfrescoService.uploadFile(p.getUsername(), filename, UUID.randomUUID().toString(), file);
            uploadFlie.setStatus("SUCCESS");
        	uploadFlie.setMessage("");
            return new ResponseEntity<>(uploadFlie, HttpStatus.OK);
        } catch (Exception e) {
        	uploadFile.setMessage("No pudimos subir su archivo");
            return new ResponseEntity<>(uploadFile, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/download/{documentId}")
    public void downloadFile(@PathVariable("documentId") String documentId,
                                                @RequestParam("filename") String filename,
                                                HttpServletResponse response) {
        AlfrescoFileRestOutRO downloadFile = new AlfrescoFileRestOutRO();
        downloadFile.setStatus("ERROR");
        downloadFile.setMessage("No se encuentra el archivo");
        try {
            byte[] data = alfrescoService.getBytesNode(documentId);
            
            response.setContentType("application/octet-stream");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
            
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    
}
