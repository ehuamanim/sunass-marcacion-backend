package pe.gob.sunass.marcacion.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
import pe.gob.sunass.marcacion.constant.DataBase;
import pe.gob.sunass.marcacion.constant.PropertiesConstant;
import pe.gob.sunass.marcacion.httpconnection.RequestConnection;
import pe.gob.sunass.marcacion.httpconnection.SecretList;
import pe.gob.sunass.marcacion.httpconnection.SunassRequest;
import pe.gob.sunass.marcacion.httpconnection.facade.SecretFacade;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef= "dbEntityManagerFactory", 
		transactionManagerRef = "dbTransactionManager",
		basePackages = {"pe.gob.sunass.marcacion.repository"})
public class DbMarcacion {

    @Autowired
    private PropertiesConstant props;

    @Bean(name="dbDs")
	public DataSource dataSource() throws Exception {
        SunassRequest<String> sunreq = new SunassRequest<>(props.getUrl());
        RequestConnection requestBody = new RequestConnection( props.getToken(), props.getEnv() );
        String response = sunreq.postConnection(requestBody, String.class);
        
        SecretList secretList = SecretFacade.parseToList(response);
        // String jdbcUrl  = SecretFacade.getValueFromName(secretList, DataBase.CNX_STRING);
        String jdbcUrl  = "jdbc:oracle:thin:@10.10.3.73:1521:trass";
        String username = SecretFacade.getValueFromName(secretList, DataBase.USERNAME);
        String password = SecretFacade.getValueFromName(secretList, DataBase.PASSWORD);

        return DataSourceBuilder.create()
                  .url( jdbcUrl )
				  .username( username )
				  .password( password )
				  .driverClassName(props.getDriverClassname()).build();
    }

    @Bean(name = "dbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(props.getPackageToScan());

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setDatabasePlatform( props.getDbPlatform() );

        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

    @Bean(name="dbTransactionManager")
	public PlatformTransactionManager platformTransactionManager(@Qualifier("dbEntityManagerFactory") EntityManagerFactory entityManagerFactory){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
	}
}
