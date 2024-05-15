package pe.gob.sunass.marcacion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import pe.gob.sunass.marcacion.constant.PropertiesConstant;
import pe.gob.sunass.marcacion.httpconnection.RequestConnection;
import pe.gob.sunass.marcacion.httpconnection.SecretList;
import pe.gob.sunass.marcacion.httpconnection.SunassRequest;
import pe.gob.sunass.marcacion.httpconnection.facade.SecretFacade;

@Configuration
public class AlfrescoConfig {

    @Autowired
    private PropertiesConstant props;

    @Bean
    public RestTemplate restTemplate() {

        SunassRequest<String> sunreq = new SunassRequest<>(props.getUrl());
        RequestConnection requestBody = new RequestConnection( props.getToken(), props.getEnv() );
        String response = sunreq.postConnection(requestBody, String.class);
        
        SecretList secretList = SecretFacade.parseToList(response);
        String alfUser = "umarcacionv";
        String alfPass = "W>97_X;t8FeI";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(alfUser, alfPass));
        return restTemplate;
    }

    @Bean
    public String alfrescoApiUrl() {
        SunassRequest<String> sunreq = new SunassRequest<>(props.getUrl());
        RequestConnection requestBody = new RequestConnection( props.getToken(), props.getEnv() );
        String response = sunreq.postConnection(requestBody, String.class);

        SecretList secretList = SecretFacade.parseToList(response);
        String alfUrl = "https://alfrescodev.sunass.gob.pe/alfresco";

        return UriComponentsBuilder.fromHttpUrl(alfUrl)
                .path("/api/-default-/public/alfresco/versions/1")
                .toUriString();
    }

}
