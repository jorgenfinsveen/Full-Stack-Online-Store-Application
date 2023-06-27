package no.ntnu.mocha.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import no.ntnu.mocha.domain.entities.Role;


/**
 * Representation of an authenticated user containing its username,
 * password, and a Set of authorities which the user possess. 
 * 
 * @author IDATA2306, Group 10
 * @author <a href="https://github.com/strazdinsg">strazdinsg</a>
 * @see <a href="https://github.com/strazdinsg/app-dev/blob/main/security-demos/05-jwt-authentication/src/main/java/no/ntnu/security/AccessUserDetails.java">Original author implementation</a>
 */
public class AccessUserDetails implements UserDetails {
    

    private final String USERNAME;
    private final String PASSWORD;
    private final Set<GrantedAuthority> AUTHORITIES;


    /**
     * Create new AccessUserDetails instance containing the username, password
     * and roles of the authenticated user.
     * 
     * @param username the username of the user.
     * @param password the password of the user.
     * @param roles a set containing the Role entities of the user.
     */
    public AccessUserDetails(String username, String password, Set<Role> roles) {
        this.USERNAME = username;
        this.PASSWORD = password;
        this.AUTHORITIES = new HashSet<>();

        /* Converts the Role entities into GrantedAuthority representations. */
        for (Role role : roles) {
            this.AUTHORITIES.add(new SimpleGrantedAuthority(role.getName()));
        }
    }


    /**
     * Get the username of the authenticated user.
     * 
     * @return the username of the authenticated user.
     */
    @Override public String getUsername() { return USERNAME; }

    /**
     * Get the password of the authenticated user.
     * 
     * @return the password of the authenticated user.
     */
    @Override public String getPassword() { return PASSWORD; }

    /**
     * Get the authorities of the authenticated user.
     * 
     * @return Collection of GrantedAuthority objects for the given user.
     */
    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return AUTHORITIES; }

    /**
     * Determines whether the user account is enabled.
     * 
     * @return true, as accounts cant get disabled.
     */
    @Override public boolean isEnabled() { return true; }

    /**
     * Determines whether the account is not expired.
     * 
     * @return true, as accounts does not have an expiration-date.
     */
    @Override public boolean isAccountNonExpired() { return true; }

    /**
     * Determines whether the account is unlocked.
     * 
     * @return true, as account cant get locked.
     */
    @Override public boolean isAccountNonLocked() { return true; }

    /**
     * Determines whether the account credentials is not expired.
     * 
     * @return true, as the credentials does not have an expiration-date.
     */
    @Override public boolean isCredentialsNonExpired() { return true; }
}
