package no.ntnu.mocha.service.authentication;


import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;


/**
 * Represents an JSON Web Token (JWT) service which generates
 * JWT Tokens upon user authentication, and to validate JWTs
 * provided by the client to ensure the credibility of the
 * given requests.
 *
 * @author  Group 10
 * @since   14.05.2023
 * @version 14.05.2023
 */
@Component public class JwtService {

	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private Environment environment;

	/** Time which a JWT is valid for. (1 day) */
	private static final long EXPIRATIONTIME = 86400000; 
	private static final String PREFIX = "Bearer";

	/** Byte representation of the key signature. (Key is read from properties-file.) */
	private static byte[] key = null;

	
	/**
	 * Generates a JWT for an authenticated user with their username and authorities
	 * encoded into it.
	 * 
	 * @param username the username of the authenticated user.
	 * @param password the password of the authenticated user.
	 */
	public String getToken(String username, String password) {
		
		checkKey(environment);
		UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(
			username,
			password
		);
		Authentication auth = authenticationManager.authenticate(creds);
		
		return Jwts.builder()
			.setSubject(auth.getName())
			.claim("roles", auth.getAuthorities().toString())
			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
			.signWith(Keys.hmacShaKeyFor(key), SignatureAlgorithm.HS256)
			.compact();
  	}


	/**
	 * Accepts a HTTP request and extracts the JWT and parses it into
	 * a string representation of the user which the token was given
	 * to.
	 * 
	 * @param request the HTTP request to extract the JWT from.
	 * @return String representation of the user.
	 */
	public String getAuthUser(HttpServletRequest request) {
		
		checkKey(environment);
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

		return (token == null) ? null : 
			Jwts.parserBuilder()
				.setSigningKey(Keys.hmacShaKeyFor(key))
				.build()
				.parseClaimsJws(token.replace(PREFIX + " ", ""))
				.getBody()
				.getSubject();
	}


	/**
	 * Checks that the key signature used to sign and decode JWTs has been set.
	 * If it is not, it will be assigned by reading it from the properties-file.
	 * 
	 * @param environment the environment object which properties can be read from.
	 */
	private static void checkKey(Environment environment) {
		if (key == null) {
			String encodedKey = environment.getProperty("jwt.secret.key");
			if (encodedKey == null) throw new NullPointerException("JWT: Secret key not specified.");
			key = Base64.getDecoder().decode(encodedKey);
		}
	}
}
