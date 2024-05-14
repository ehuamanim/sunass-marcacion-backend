package pe.gob.sunass.marcacion.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import pe.gob.sunass.marcacion.constant.AppConstant;
import pe.gob.sunass.marcacion.model.Personal;
import pe.gob.sunass.marcacion.service.PersonalRemotoService;
import pe.gob.sunass.marcacion.service.UsuarioService;

@Component
public class UserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{
	
	private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
    private UsuarioService _usuarioService;

	@Autowired
	private PersonalRemotoService personalRemotoService;
	
	private String userNotFoundEncodedPassword;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
		if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");
			throw new BadCredentialsException(
					messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}

		String presentedPassword = authentication.getCredentials().toString();
		String passEncoder = passwordEncoder.encode( userDetails.getPassword() );

		if (!passwordEncoder.matches(presentedPassword, passEncoder )) {
			logger.debug("Authentication failed: password does not match stored value");
			throw new BadCredentialsException(
					messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
	}
	
	@Override
	protected void doAfterPropertiesSet() throws Exception {
		this.userNotFoundEncodedPassword = this.passwordEncoder.encode(USER_NOT_FOUND_PASSWORD);
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
		UserAuthenticationToken authentication2 = (UserAuthenticationToken) authentication;
		final String password = (String) authentication2.getCredentials();
		
		Personal userRs = null;
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		try {
			Optional<Personal> optUsuario = _usuarioService.findByUserNameLDAP(username, password, passwordEncoder.encode(password));
			
			if(optUsuario.isPresent()) {
				userRs = optUsuario.get();

				// Validando si el usuario tiene rol asignado
				if( userRs.getRol() == null ){
					throw new BadCredentialsException("Usuario no tiene rol asignado");
				}

				if( userRs.getRol().toUpperCase().equals( AppConstant.ROL_USER ) ){
					boolean tieneDiaAsignado = personalRemotoService.personalTieneDiaAsignado(userRs.getPersonalId());
					if( !tieneDiaAsignado ){
						throw new BadCredentialsException("Usuario no tiene dia asignado para remoto");
					}
				}
				authorities.add(new SimpleGrantedAuthority(userRs.getRol()));
			} else {
				throw new BadCredentialsException("Usuario y/o contraseña incorrecto");
			}
		
		} catch (UsernameNotFoundException ex) {
			if (password != null) {
				String presentedPassword = authentication.getCredentials().toString();
				passwordEncoder.matches(presentedPassword, userNotFoundEncodedPassword);
			}
			throw ex;
		} catch (Exception ex) {
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
		}
		
		return new User(userRs.getUsername(), password, authorities);
	}

}
