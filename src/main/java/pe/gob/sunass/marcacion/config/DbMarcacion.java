package pe.gob.sunass.marcacion.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import pe.gob.sunass.marcacion.constant.DataBase;
import pe.gob.sunass.marcacion.httpconnection.ItemConnection;
import pe.gob.sunass.marcacion.httpconnection.RequestConnection;
import pe.gob.sunass.marcacion.httpconnection.ResponseConnection;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef= "dbEntityManagerFactory", 
		transactionManagerRef = "dbTransactionManager",
		basePackages = {"pe.sob.sunass.marcacion.repository"})
public class DbMarcacion {

    @Value("${db.datasource.remote-secret-ops}")
    private String url;

    @Value("${db.datasource.token}")
    private String token;

    @Value("${db.datasource.environment}")
    private String env;

    @Value("${db.datasource.driver-class-name}")
    private String dsDriverClassname;

    @Value("${db.jpa.database-platform}")
    private String dsDbPlatform;

    @Value("${db.jpa.show-sql}")
    private String dsShowSql;


    @Bean(name="dbDs")
	@ConfigurationProperties(prefix="db.datasource")
	public DataSource dataSource() throws Exception {

        RequestConnection requestBody = new RequestConnection( token, env );
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RequestConnection> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<ResponseConnection> response = restTemplate.postForEntity(url, request, ResponseConnection.class);
        ResponseConnection responseConnection = response.getBody();
        
        if (responseConnection == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        
        String jdbcUrl = responseConnection.getItemFromName(DataBase.CNX_STRING.value()).getSecretValue();
        String username = responseConnection.getItemFromName(DataBase.USERNAME.value()).getSecretValue();
        String password = responseConnection.getItemFromName(DataBase.PASSWORD.value()).getSecretValue();

        System.err.println( responseConnection.toString() );

        return DataSourceBuilder.create()
                  .url( jdbcUrl )
				  .username( username )
				  .password( password )
				  .driverClassName(dsDriverClassname).build();
    }

}
