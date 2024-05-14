package pe.gob.sunass.marcacion.security.dto;

import jakarta.validation.constraints.NotEmpty;

public class JwtTokenDTO {
	
	@NotEmpty
	private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
