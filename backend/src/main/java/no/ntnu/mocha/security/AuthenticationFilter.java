package no.ntnu.mocha.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import no.ntnu.mocha.service.authentication.JwtService;
import no.ntnu.mocha.service.authentication.UserDetailsServiceImpl;



/**
 * Represents a filter class to authenticate incoming requests.
 * The AuthencticationFilter class extends Spring Security's 
 * OncePerRequestFilter interface, which provides doFilterInternal
 * method where the authentication is implemented.
 * 
 * </p>The software is inspired by an example project authored by 
 * Packt Publishing. Original source is referenced to.
 * 
 * @author  <a href="https://github.com/PacktPublishing">PacktPublishing</a>
 * @author  Group 10
 * @since   14.05.2023
 * @version 29.05.2023
 * @see <a href="https://github.com/PacktPublishing/Full-Stack-Development-with-Spring-Boot-and-React/blob/main/Chapter05/src/main/java/com/packt/cardatabase/AuthenticationFilter.java">Reference</a>
 */
@Component public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired private JwtService jwtService;
    @Autowired private UserDetailsServiceImpl userDetailsService;


    /**
     * Filters communication between client and server, ensuring that the client is authenticated
     * when required, and holds the required authorities.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException 
    {
        if (request.getHeader(HttpHeaders.AUTHORIZATION) != null) {
            String user = jwtService.getAuthUser(request);
            UserDetails details = userDetailsService.loadUserByUsername(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                user,
                null,
                details.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        filterChain.doFilter(request, response);
    }
}