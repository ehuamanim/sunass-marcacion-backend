package pe.gob.sunass.marcacion.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import javax.naming.directory.DirContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.sunass.marcacion.common.Ldap;
import pe.gob.sunass.marcacion.model.Personal;
import pe.gob.sunass.marcacion.repository.PersonalRepository;

@Service
public class UsuarioService {

	@Autowired
	PersonalRepository _personalRepository;

	@Autowired
	Ldap ldapUtil;

	

	public Optional<Personal> findByUserNameLDAP(String userName, String password, String passwordEncode) {

		Personal item = null;
		try {
			if (!userName.trim().equals("") && !password.trim().equals("")) {
				DirContext context = this.validarWithOutSimpleAuthenticacion(userName, password);
				if (context != null) {
					item = _personalRepository.findByUsername(userName);
				}
			}
		} catch (Exception ex) {
			item = null;
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
		}

		Optional<Personal> opt = Optional.ofNullable(item);
		return opt;
	}

	private DirContext validarWithOutSimpleAuthenticacion(String userName, String password) throws Exception {
		DirContext context = null;
		try {
			context = ldapUtil.validarWithOutSimpleAuthenticacion(userName, password);
			return context;
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (context != null) {
				context.close();
			} else {
				return null;
			}
		}
	}
}
