package no.ntnu.mocha.security;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Eventhandler which is responsible for responding with HTTP 401 Unauthorized upon
 * the server receiving a client request which does not meet the authentication
 * requirements.
 * 
 * </p>The software is inspired by an example project authored by 
 * Packt Publishing. Original source is referenced to.
 * 
 * @author  <a href="https://github.com/PacktPublishing">PacktPublishing</a>
 * @author  Group 10
 * @see <a href="https://github.com/PacktPublishing/Full-Stack-Development-with-Spring-Boot-and-React/blob/main/Chapter05/src/main/java/com/packt/cardatabase/AuthEntryPoint.java">Inspiration</a>
 */
@Component public class AuthEntryPoint implements AuthenticationEntryPoint {
    

    /**
     * Overrides the original server response to an HTTP request with code 401 Unauthorized 
     * upon receiving a request which does not meet the required degree of authentication.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException 
    {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().println("Error: " + authException.getMessage());
    }
}
