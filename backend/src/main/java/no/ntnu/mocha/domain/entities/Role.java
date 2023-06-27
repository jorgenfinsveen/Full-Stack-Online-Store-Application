package no.ntnu.mocha.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * User role (admin, regular user, etc).
 */
@Entity
@Table(name = "roles")
public class Role {
    
    /** Primary key of the entity. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    /** Name of the entity. */
    @Column(unique = true, nullable = false)
    private String name;


    /**
     * Empty constructor.
     */
    public Role() {}


    /**
     * Creates a new Role entity.
     * 
     * @param name the name of the role.
     */
    public Role(String name) {
        super();
        this.setName(name);
    }


    /**
     * Get the ID of role.
     * 
     * @return ID of the role.
     */
    public Long getId() {
        return id;
    }


    /**
     * Get the name of the role.
     * 
     * @return name of the role.
     */
    public String getName() {
        return name;
    }


    /**
     * Set the name of the role.
     * 
     * @param name the name of the role.
     */
    public void setName(String name) {
        this.name = name.toUpperCase();
        if (!this.name.startsWith("ROLE_")) {
            this.name = "ROLE_" + name.toUpperCase();
        }
    }
}
