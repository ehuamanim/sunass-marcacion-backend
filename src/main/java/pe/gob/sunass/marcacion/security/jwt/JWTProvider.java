package pe.gob.sunass.marcacion.security.jwt;

import java.security.Key;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import pe.gob.sunass.marcacion.model.Personal;
import pe.gob.sunass.marcacion.security.dto.JwtTokenDTO;

@Component
public class JWTProvider {

	@Value("${security.jwt.expiration-minutes}")
    private long EXPIRATION_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;
    
    public String generateToken(Personal user) {

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date( issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000) );

        Map<String, Object> extraClaims = new HashMap<>();
        
        extraClaims = this.generateExtraClaims(user);
        
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key generateKey(){
        byte[] secretAsBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretAsBytes);
    }

    public String extractUsername(String jwt){
        return extractAllClaims(jwt).getSubject();
    }

    public String extractUsernameFromExpired(String jwtExpired) throws ParseException{
    	
    	JWT jwt = JWTParser.parse(jwtExpired);
    	
    	JWTClaimsSet claims = jwt.getJWTClaimsSet();
        
    	return claims.getSubject();
    }
    
    private Claims extractAllClaims(String jwt){
    	return Jwts.parserBuilder().setSigningKey(generateKey()).build()
                .parseClaimsJws(jwt).getBody();
    }
    
    public boolean isEnabledToRefresh(String token) throws ParseException{
    	
    	JWT jwt = JWTParser.parse(token);
    	JWTClaimsSet claims = jwt.getJWTClaimsSet();
    	Date isNow = new Date(System.currentTimeMillis());
    	Date limitToRefresh = new Date(claims.getIssueTime().getTime() + (EXPIRATION_MINUTES * 2 * 60 * 1000) );
    	
    	return isNow.getTime() <= limitToRefresh.getTime() ? true : false;
    }
    
    public String refreshToken(JwtTokenDTO jwtTokenDto, Personal usuarioRefresh) throws ParseException{
    	
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date( issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000) );

        try {
            Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(jwtTokenDto.getJwt()).getBody();
        }
        catch(io.jsonwebtoken.ExpiredJwtException e) {
        
        	JWT jwt = JWTParser.parse(jwtTokenDto.getJwt());
        	JWTClaimsSet claims = jwt.getJWTClaimsSet();
            String username = claims.getSubject();
            
            Map<String, Object> extraClaims = new HashMap<>();
            
            extraClaims = this.generateExtraClaims(usuarioRefresh);
        	
	        return Jwts.builder()
	                .setClaims(extraClaims)
	                .setSubject(username)
	                .setIssuedAt(issuedAt)
	                .setExpiration(expiration)
	                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
	                .signWith(generateKey(), SignatureAlgorithm.HS256)
	                .compact();
        }
        return null;
    }
    
    private Map<String, Object> generateExtraClaims(Personal user) {

        Map<String, Object> extraClaims = new HashMap<>();
        List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority( user.getRol().toUpperCase()) );
        extraClaims.put("documento", user.getNroDoc() );
        extraClaims.put("role", user.getRol() );
        extraClaims.put("permissions", authorities );

        return extraClaims;
    }

}
