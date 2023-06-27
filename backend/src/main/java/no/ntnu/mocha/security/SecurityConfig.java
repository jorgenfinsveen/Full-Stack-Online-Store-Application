package no.ntnu.mocha.security;



import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import no.ntnu.mocha.service.authentication.UserDetailsServiceImpl;


/**
 * Security configuration for the Spring Boot application.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity( jsr250Enabled = true, prePostEnabled = true, securedEnabled = false)
public class SecurityConfig {


    @Autowired private UserDetailsServiceImpl userDetailsService;
    @Autowired private AuthenticationFilter authenticationFilter;
    @Autowired private AuthEntryPoint exceptionHandler;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();



    /**
     * Configures the authentication strategies for the application. Sets
     * the UserDetailsServiceImpl class for UserDetailsService and the
     * BCryptPasswordEncoder for password encryption/decryption.
     *
     * @param auth AuthenticationManagerBuilder to use for the configuration.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }



    /**
     * Configures the AuthenticationManager instance and returns it.
     * 
     * @param http HttpSecurity instance to configure the AuthenticationManager instance.
     * @return AuthenticationManager instance.
     */
    @Bean
    public AuthenticationManager getAuthenticationManager(HttpSecurity http) throws Exception {

        http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder);
        
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }



    /**
     * Configures the primary security measures of the application, including CORS, CSRF, 
     * exceptionhandling and endpoint protection with required authorities and authentication.
     *
     * @param http HttpSecurity setting builder
     * @throws Exception
     */
    @Bean
    @SuppressWarnings(value = { "all" })
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        return http.csrf(csrf -> csrf.disable())
            .cors(withDefaults())
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize

                /* PUBLIC endpoints. */
                .requestMatchers(HttpMethod.POST, "/login", "/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/products", "/products/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/reviews").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/**", "/v3/api-docs").permitAll()
                .requestMatchers("/api-docs/**", "/v3/api-docs/swagger-config").permitAll()
                

                /* ADMIN endpoints. */
                .requestMatchers(HttpMethod.GET, "/orders").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/products/admin").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/products/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")


                /* All other endpoints must be authenticated and contain the role "USER". */
                .anyRequest()
                .authenticated()
            )
            .exceptionHandling(handling -> handling.authenticationEntryPoint(exceptionHandler))
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }



    /**
     * Returns a CorsConfigurationSource object that is used to configure
     * Cross-Origin Resource Sharing (CORS) for the application.
     * The configuration sets allowed origins, methods, and headers and
     * specifies whether or not to allow credentials.
     * 
     * @return a CorsConfigurationSource object with CORS configuration settings
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(false);
        config.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
