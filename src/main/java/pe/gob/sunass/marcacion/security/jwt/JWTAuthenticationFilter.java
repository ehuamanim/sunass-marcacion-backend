package pe.gob.sunass.marcacion.security.jwt;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.gob.sunass.marcacion.constant.DefaultMessage;
import pe.gob.sunass.marcacion.exceptions.UnAuthorizedException;
import pe.gob.sunass.marcacion.model.Personal;
import pe.gob.sunass.marcacion.repository.PersonalRepository;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
    private JWTProvider _jwtProvider;
	
	@Autowired
    private PersonalRepository _usuario;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = "";
		
		try {
			
			//1. Obtener el header que contiene el jwt
	        String authHeader = request.getHeader("Authorization");
	
	        if(authHeader == null || !authHeader.startsWith("Bearer ")){
	            filterChain.doFilter(request, response);
	            return;
	        }
	
	        //2. Obtener jwt desde header
	        jwt = authHeader.split(" ")[1];
	        
	        //3. Obtener subject/username desde el jwt
	        String username = _jwtProvider.extractUsername(jwt);
	        
	        //4. Setear un objeto Authentication dentro del SecurityContext
	        Personal user = _usuario.findByUsername(username);
			List<GrantedAuthority> authorities = new ArrayList<>();
		    authorities.add(new SimpleGrantedAuthority(user.getRol()));
	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken( username, null, authorities );
	        SecurityContextHolder.getContext().setAuthentication(authToken);
		}catch(Exception ex) {
    		if (ex instanceof ExpiredJwtException) {
    			
    			boolean enabledToRefresh = false;
    			try {
					if(_jwtProvider.isEnabledToRefresh(jwt)) {
						enabledToRefresh = true;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
    			
    			if(enabledToRefresh){
    				response.setStatus(HttpStatus.UNAUTHORIZED.value());
    			}else {
    				throw new UnAuthorizedException(DefaultMessage.ServerStatus.S401);
    			}
    			
			} else {
				throw new BadRequestException(DefaultMessage.ServerStatus.S400);
			}
			
    	}
		
		//5. Ejecutar el restro de filtros
        filterChain.doFilter(request, response);
	}
	
	public String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
        	return authHeader.split(" ")[1];
        }
        return null;
	}

}
