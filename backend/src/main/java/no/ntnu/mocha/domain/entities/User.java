package no.ntnu.mocha.domain.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Represents an User.
 * 
 * @version 21.03.2023
 * @since   21.03.2023
 */
@Entity
@Table(name = "user")
public class User implements UserDetails {
    
    /** Unique User ID. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "user_id")
    private Long id;

    /** The username of the User. */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /** The password of the User */
    @Column(name = "password")
    private String password;

    /** The email of the User */
    @Column(name = "email")
    private String email;

    /** User biography. */
    @Column(name = "bio")
    private String bio;

    /** Set of roles held by the user. */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();



    /**
     * Empty constructor.
     */
    public User() {}


    /**
     * Creates an instance of User with username and password.
     * 
     * @param username  The username of the User.
     * @param password  The pasword of the User.
     * @param roles     Set of roles held by the user.
     */
    public User(String username, String password, Set<Role> roles) {
        super();
        this.setUserName(username);
        this.setPassword(password);
        this.setRoles(roles);
    }


    /**
     * Creates an instance of User with username and password.
     * 
     * @param username  The username of the User.
     * @param password  The pasword of the User.
     * @param roles     Set of roles held by the user.
     * @param email     The email od the user.
     * @param bio       The biography of the user.
     */
    public User(String username, String password, Set<Role> roles, String email, String bio) {
        super();
        this.setUserName(username);
        this.setPassword(password);
        this.setRoles(roles);
        this.setEmail(email);
        this.setBio(bio);
    }


    /**
     * Returns the User Id for the User.
     * 
     * @return the User Id for the User.
     */
    public long getId(){
        return id;
    }


    /**
     * Get the username of the user.
     * 
     * @return user's username.
     */
    public String getUsername(){
        return username;
    }


    /**
     * Set the username of the user.
     * 
     * @param username the new username.
     */
    private void setUserName(String username) {
        this.username = username;
    }


    /**
     * Returns the password for the User.
     * 
     * @return the password for the User.
     */
    public String getPassword() {
        return password;
    }


    /**
     * Sets the password for the User.
     * 
     * @param password the password for the User.
     */
    private void setPassword(String password) {
        this.password = password;
    }


    /**
     * Returns the email of the User.
     * 
     * @return the email of the User.
     */
    public String getEmail() {
        return email;
    }


    /**
     * Sets the email of the User.
     * 
     * @param email the email of the User.
     */
    private void setEmail(String email) {
        this.email = email;
    }


    /**
     * Get the biography of the User.
     * 
     * @return the bio of the user.
     */
    public String getBio() {
        return bio;
    }


    /**
     * Set the biography of the user.
     * 
     * @param bio the bio of the user.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }


    /**
     * Get the roles of the user.
     * 
     * @return list of user roles.
     */
    public Set<Role> getRoles() {
        return roles;
    }


    /**
     * Set the roles of the user.
     * 
     * @param roles the roles of the user.
     */
    private void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}
