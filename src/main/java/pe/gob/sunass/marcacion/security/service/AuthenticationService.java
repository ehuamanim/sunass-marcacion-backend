 package pe.gob.sunass.marcacion.security.service;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import pe.gob.sunass.marcacion.model.Personal;
import pe.gob.sunass.marcacion.repository.PersonalRepository;
import pe.gob.sunass.marcacion.security.UserAuthenticationManager;
import pe.gob.sunass.marcacion.security.UserAuthenticationToken;
import pe.gob.sunass.marcacion.security.dto.AuthenticationRequestDTO;
import pe.gob.sunass.marcacion.security.dto.AuthenticationResponseDTO;
import pe.gob.sunass.marcacion.security.dto.JwtTokenDTO;
import pe.gob.sunass.marcacion.security.jwt.JWTProvider;

@Service
public class AuthenticationService {
	
    @Autowired
    private UserAuthenticationManager authenticationManager;

    @Autowired
    private PersonalRepository usuarioRepository;

    @Autowired
    private JWTProvider _jwtProvider;
    
    public AuthenticationResponseDTO login(AuthenticationRequestDTO authRequest) {
    	
    	UserAuthenticationToken authInputToken = new UserAuthenticationToken((Object) authRequest.getUsername(), (Object) authRequest.getPassword());
    	Authentication authentication = authenticationManager.authenticate(authInputToken);
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	String userName = (String) authInputToken.getPrincipal();
        Personal user = this.findByUsername(userName);
        String jwt = _jwtProvider.generateToken(user);

        return new AuthenticationResponseDTO(jwt);
    }
    
    public Personal findByUsername(String username) {
    	return usuarioRepository.findByUsername(username);
    }
    
	public Personal getPersonal(){
		String username = getUsername();
		return findByUsername(username);
	}
    
    public String getUsername() {

       SecurityContext context = SecurityContextHolder.getContext();
       Authentication authentication = context.getAuthentication();
	   
       if (authentication == null){
		   return null;
	   }
	   
       Object principal = authentication.getPrincipal();
	   if (principal instanceof UserDetails) {
	     return ((UserDetails) principal).getUsername();
	   } else {
	     return principal.toString();
	   }
    }
    
    public String getUsernameFromExpired(JwtTokenDTO jwtTokenDTO) throws ParseException {
    	return _jwtProvider.extractUsernameFromExpired(jwtTokenDTO.getJwt());
     }
    
    public AuthenticationResponseDTO refreshToken(JwtTokenDTO jwtTokenDTO) throws ParseException {
    	
    	String userName = this.getUsernameFromExpired(jwtTokenDTO);
    	Personal user = this.findByUsername(userName);
    	String jwt = null;
    	boolean enabledToRefresh = false;
		
		try {
			if(_jwtProvider.isEnabledToRefresh(jwtTokenDTO.getJwt())) {
				enabledToRefresh = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(enabledToRefresh){
			jwt = _jwtProvider.refreshToken(jwtTokenDTO, user);
		}
    	
    	return new AuthenticationResponseDTO(jwt);
    }
	
    
    public String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
        	return authHeader.split(" ")[1];
        }
        return null;
	}
}
