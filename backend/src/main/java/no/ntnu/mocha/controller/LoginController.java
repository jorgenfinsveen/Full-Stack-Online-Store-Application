package no.ntnu.mocha.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import no.ntnu.mocha.DTO.CredentialsDto;
import no.ntnu.mocha.service.authentication.JwtService;


/**
 * Represents a Login Controller class that provides RESTful
 * services to the /login endpoint with HTTP /POST mapping
 * for login requests.
 * 
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    
    @Autowired private JwtService jwtService;

	/**
     * Receives an HTTP POST request with the user's credentials and authenticates them. If the
     * authentication is successful, it generates a token and sets the budget ID and authorization headers.
     *
     * @param credentials An object that holds the user's credentials (username and password).
     * @return An HTTP response entity that includes a token in the Authorization header and the budget ID.
     */
    @PostMapping
	@Operation(
        summary = "Login",
        description = "Attempts login through authentication of user credentials."
    )
	public ResponseEntity<?> login(@Parameter(description = "User credentials.") @RequestBody CredentialsDto dto) {

		/* Authenticate and generate JSON Web Token. */
		String jwts = jwtService.getToken(dto.getUsername(), dto.getPassword());

		return ResponseEntity.ok()
			.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
			.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
			.build();
	}
}
