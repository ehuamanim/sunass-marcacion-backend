package pe.gob.sunass.marcacion.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PropertiesConstant {

    @Value("${db.datasource.remote-secret-ops}")
    private String url;

    @Value("${db.datasource.token}")
    private String token;

    @Value("${db.datasource.environment}")
    private String env;

    @Value("${db.datasource.driver-class-name}")
    private String driverClassname;

    @Value("${db.jpa.database-platform}")
    private String dbPlatform;

    @Value("${db.jpa.show-sql}")
    private String showSql;

    @Value("${db.jpa.package-to-scan}")
    private String packageToScan;

    @Value("${db.hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Value("${file.path}")
    private String filePath;

    @Value("${cors.urls}")
    private String corsUrl;

    @Value("${mensaje.login}")
    private String mensajeLogin;

    @Value("${mensaje.logout}")
    private String mensajeLogout;

    @Value("${mensaje.endwork}")
    private String mensajeEndWork;

}
