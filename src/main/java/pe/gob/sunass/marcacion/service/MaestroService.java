package pe.gob.sunass.marcacion.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.sunass.marcacion.constant.AppConstant;
import pe.gob.sunass.marcacion.dto.MaestroRestOutDto;
import pe.gob.sunass.marcacion.repository.CargoRepository;
import pe.gob.sunass.marcacion.repository.CondicionRepository;
import pe.gob.sunass.marcacion.repository.EjercicioRepository;
import pe.gob.sunass.marcacion.repository.HorarioRepository;
import pe.gob.sunass.marcacion.repository.InstitucionSedeRepository;
import pe.gob.sunass.marcacion.repository.MotivoNoCumplimientoRepository;
import pe.gob.sunass.marcacion.repository.PlanillaRepository;
import pe.gob.sunass.marcacion.repository.SituacionRepository;
import pe.gob.sunass.marcacion.repository.TipoDiaRepository;
import pe.gob.sunass.marcacion.repository.TipoDocumentoRepository;
import pe.gob.sunass.marcacion.repository.TipoSuspensionRepository;
import pe.gob.sunass.marcacion.repository.TipoTrabajadorRepository;
import pe.gob.sunass.marcacion.repository.UoRepository;

@Service
public class MaestroService {

	@Autowired
    private ActividadService actividadService;
	
	@Autowired
	private MotivoNoCumplimientoRepository motivoNoCumplimientoRepository;

	@Autowired
	private EjercicioRepository ejercicioRepository;

	@Autowired
	private PlanillaRepository planillaRepository;

	@Autowired
	private InstitucionSedeRepository institucionSedeRepository;

	@Autowired
	private CargoRepository cargoRepository;

	@Autowired
	private CondicionRepository condicionRepository;

	@Autowired
	private SituacionRepository situacionRepository;

	@Autowired
	private TipoTrabajadorRepository tipoTrabajadorRepository;

	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

	@Autowired
	private TipoDiaRepository tipoDiaRepository;

	@Autowired
	private UoRepository uoRepository;

	@Autowired
	private TipoSuspensionRepository tipoSuspensionRepository;

	@Autowired
	private HorarioRepository horarioRepository;
	
	public List<MaestroRestOutDto> listAll( String tipo ){
		
		List<MaestroRestOutDto> maestro = new ArrayList<MaestroRestOutDto>();
		Map<String, Supplier<List<MaestroRestOutDto>>> maestroMap = new HashMap<>();

		maestroMap.put(AppConstant.MAESTRO_ACTIVIDADES, () -> actividadService
																.listAll()
																.stream()
       															.map(a -> new MaestroRestOutDto(a.getActividadId(), a.getDescripcion(), a.getFlag()))
       															.collect(Collectors.toList()));

		maestroMap.put(AppConstant.MAESTRO_MOTIVO_NO_C, () -> motivoNoCumplimientoRepository
																.findAll()
																.stream()
       															.map(a -> new MaestroRestOutDto(a.getMotivoId(), a.getDescripcion(), a.getFlag()))
       															.collect(Collectors.toList()));

		maestroMap.put(AppConstant.MAESTRO_CARGO, () -> cargoRepository
																.findAll()
																.stream()
       															.map(a -> new MaestroRestOutDto(a.getCargoId(), a.getDescCargo(), a.getFlag()))
       															.collect(Collectors.toList()));

		maestroMap.put(AppConstant.MAESTRO_EJERCICIO, () -> ejercicioRepository
																.findAll()
																.stream()
       															.map(a -> new MaestroRestOutDto(a.getEjercicioId(), a.getDescripcion(), a.getFlag()))
       															.collect(Collectors.toList()));

		maestroMap.put(AppConstant.MAESTRO_PLANILLA, () -> planillaRepository
																.findAll()
																.stream()
       															.map(a -> new MaestroRestOutDto(a.getPlanillaId(), a.getDescripcion(), a.getFlag()))
       															.collect(Collectors.toList()));

		maestroMap.put(AppConstant.MAESTRO_INSTITUCION, () -> institucionSedeRepository
																.findAll()
																.stream()
       															.map(a -> new MaestroRestOutDto(a.getSedeId(), a.getNomsede(), a.getFlag()))
       															.collect(Collectors.toList()));

		maestroMap.put(AppConstant.MAESTRO_CONDICION, () -> condicionRepository
																.findAll()
																.stream()
       															.map(a -> new MaestroRestOutDto(a.getCondicionId(), a.getDescripcion(), a.getFlag()))
       															.collect(Collectors.toList()));

		maestroMap.put(AppConstant.MAESTRO_SITUACION, () -> situacionRepository
																.findAll()
																.stream()
       															.map(a -> new MaestroRestOutDto(a.getSituacionEspId(), a.getDescripcion(), a.getFlag()))
       															.collect(Collectors.toList()));

		maestroMap.put(AppConstant.MAESTRO_TIPO_TRABAJADOR, () -> tipoTrabajadorRepository
																.findAll()
																.stream()
       															.map(a -> new MaestroRestOutDto(a.getTipoPersonaId(), a.getDescTipoTrabajador(), a.getFlag()))
       															.collect(Collectors.toList()));

		maestroMap.put(AppConstant.MAESTRO_TIPO_DOCUMENTO, () -> tipoDocumentoRepository
																.findAll()
																.stream()
       															.map(a -> new MaestroRestOutDto(a.getTipoDocId(), a.getDescTipoDocumento(), a.getFlag()))
       															.collect(Collectors.toList()));

		maestroMap.put(AppConstant.MAESTRO_UNIDAD_ORGANIZ, () -> uoRepository
																.findAll()
																.stream()
       															.map(a -> new MaestroRestOutDto(a.getItemUo(), a.getDescUnidadOrganizativa(), a.getFlag()))
       															.collect(Collectors.toList()));

		maestroMap.put(AppConstant.MAESTRO_TIPO_DIA, () -> tipoDiaRepository
																.findAll()
																.stream()
       															.map(a -> new MaestroRestOutDto(a.getTipoDiaId(), a.getDescripcion(), AppConstant.FLAG_ACTIVO))
       															.collect(Collectors.toList()));

		maestroMap.put(AppConstant.MAESTRO_TIPO_SUSPENSION, () -> tipoSuspensionRepository
																.findAll()
																.stream()
       															.map(a -> new MaestroRestOutDto(a.getTipoSuspensionRlId(), a.getDescripcion(), AppConstant.FLAG_ACTIVO))
       															.collect(Collectors.toList()));

		maestroMap.put(AppConstant.MAESTRO_HORARIOS, () -> horarioRepository
																.findAll()
																.stream()
       															.map(a -> new MaestroRestOutDto(a.getItemHorario(), a.getDescripcion(), a.getFlag()))
       															.collect(Collectors.toList()));

		Supplier<List<MaestroRestOutDto>> maestroSupplier = maestroMap.get(tipo);
		
		if (maestroSupplier!= null) {
			maestro = maestroSupplier.get();
		}
    	
    	return maestro;
	}
	
}
