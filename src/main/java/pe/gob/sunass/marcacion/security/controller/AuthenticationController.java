package pe.gob.sunass.marcacion.security.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pe.gob.sunass.marcacion.constant.DefaultMessage;
import pe.gob.sunass.marcacion.dto.PersonalDto;
import pe.gob.sunass.marcacion.facade.PersonalFacade;
import pe.gob.sunass.marcacion.model.Personal;
import pe.gob.sunass.marcacion.security.dto.AuthenticationRequestDTO;
import pe.gob.sunass.marcacion.security.dto.AuthenticationResponseDTO;
import pe.gob.sunass.marcacion.security.dto.JwtTokenDTO;
import pe.gob.sunass.marcacion.security.service.AuthenticationService;
import pe.gob.sunass.marcacion.security.util.ResponseEntity;
import pe.gob.sunass.marcacion.service.PersonalService;



@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
    private PersonalService personalService;

	@Autowired
    private PersonalFacade personalFacade;

	@Autowired
    private AuthenticationService authenticationService;
	
	@PostMapping( name = "/", produces = { MediaType.APPLICATION_JSON_VALUE } )
    public ResponseEntity<?> login( @RequestBody @Valid AuthenticationRequestDTO authRequest){

		try {
			AuthenticationResponseDTO jwtDto = authenticationService.login(authRequest);
			Map<String, Object> datos = new HashMap<>();
			Personal personal = authenticationService.getPersonal();
			PersonalDto personalDto = personalService.findAllByNroDoc( personal.getNroDoc() );
        	
			personalFacade.startSesion( personalDto );
			datos.put("token", jwtDto.getJwt());
			datos.put("personal", personalDto);

			ResponseEntity<?> response = new ResponseEntity<>();
			response.setValue( datos );
			return response;
		} catch (InternalAuthenticationServiceException ex) {
			return ResponseEntity.error(DefaultMessage.Error.customMessage(ex.getMessage()));
		} catch (Exception ex) {
			return ResponseEntity.error(DefaultMessage.Error.customMessage("Ocurrió un error al autenticarse"));
		}
		
    }
	
	@RequestMapping(value = "/refresh-token", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> refreshToken(@RequestBody @Valid JwtTokenDTO jwtToken) throws ParseException{
		try {
			AuthenticationResponseDTO jwtDto = authenticationService.refreshToken(jwtToken);
			ResponseEntity<?> response = new ResponseEntity<>();
			response.setValue(jwtDto.getJwt());
			return response;
		} catch (Exception ex) {
			if (ex instanceof InternalAuthenticationServiceException){
				return ResponseEntity.error(DefaultMessage.Error.customMessage(ex.getMessage()));
			}
			return ResponseEntity.error(DefaultMessage.Error.customMessage("Ocurrió un error de autenticacion"));
		}
	}

}
