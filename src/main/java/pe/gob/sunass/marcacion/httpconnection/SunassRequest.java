package pe.gob.sunass.marcacion.httpconnection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SunassRequest<T> {

    private String url;

    public SunassRequest( String url ){
        this.url = url;
    }

    public T postConnection( Object payload, Class<T> responseType ){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(payload, headers);
        ResponseEntity<T> response = restTemplate.postForEntity(url, request, responseType);

        if (response == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        return response.getBody();
    }

}
