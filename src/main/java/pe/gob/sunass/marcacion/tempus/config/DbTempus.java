package pe.gob.sunass.marcacion.tempus.config;

import javax.sql.DataSource;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import pe.gob.sunass.marcacion.constant.PropertiesConstant;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "dbTempusEntityManagerFactory",
    transactionManagerRef = "dbTempusTransactionManager",
    basePackages = { "pe.gob.sunass.marcacion.tempus.repository" }
)
public class DbTempus {

    @Autowired
    private PropertiesConstant props;

    @Bean(name="dbTempusDataSource")
    public DataSource dataSource() {
        
        return DataSourceBuilder.create()
                  .url( props.getDbTempusJdbcUrl() )
				  .username( props.getDbTempusUsername() )
				  .password( props.getDbTempusPassword() )
				  .driverClassName(props.getDbTempusDriver()).build();
    }

    @Bean(name = "dbTempusEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(props.getDbTempusPackageToScan());

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setDatabasePlatform( props.getDbTempusJpaDBPlatform() );

        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

    @Bean(name="dbTempusTransactionManager")
	public PlatformTransactionManager platformTransactionManager(@Qualifier("dbTempusEntityManagerFactory") EntityManagerFactory entityManagerFactory){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
	}
}
