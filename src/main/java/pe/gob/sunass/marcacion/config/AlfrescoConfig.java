package pe.gob.sunass.marcacion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import pe.gob.sunass.marcacion.constant.PropertiesConstant;
import pe.gob.sunass.marcacion.constant.SecretsEnum;
import pe.gob.sunass.marcacion.httpconnection.RequestConnection;
import pe.gob.sunass.marcacion.httpconnection.SecretList;
import pe.gob.sunass.marcacion.httpconnection.SunassRequest;
import pe.gob.sunass.marcacion.httpconnection.facade.SecretFacade;

@Component
public class AlfrescoConfig {

	@Autowired
    private PropertiesConstant props;
	
	private volatile SecretList secretList = null;
	
	@Bean
	public String getUrl() {
		ensureSecretsLoaded();
		return SecretFacade.getValueFromName(secretList, SecretsEnum.ALF_URL);
	}
	
	@Bean
	public String getUsername() {
		ensureSecretsLoaded();
		return SecretFacade.getValueFromName(secretList, SecretsEnum.ALF_USER);
	}
	
	@Bean
	public String getPassword() {
		ensureSecretsLoaded();
		return SecretFacade.getValueFromName(secretList, SecretsEnum.ALF_PASSWORD);
	}
	
	@Bean
	public String getRootFolderName() {
		ensureSecretsLoaded();
		return SecretFacade.getValueFromName(secretList, SecretsEnum.ALF_FOLDER_ROOT_NAME);
	}
	
	@Bean
	public String getRootFolderId() {
		ensureSecretsLoaded();
		return SecretFacade.getValueFromName(secretList, SecretsEnum.ALF_FOLDER_ID);
	}
	
	private synchronized void ensureSecretsLoaded() {
		if( secretList != null ) {
			return;
		}
		SunassRequest<String> sunreq = new SunassRequest<>(props.getUrl());
		RequestConnection requestBody = new RequestConnection( props.getToken(), props.getEnv() );
		String response = sunreq.postConnection(requestBody, String.class);
		this.secretList = SecretFacade.parseToList(response);
	}
}
