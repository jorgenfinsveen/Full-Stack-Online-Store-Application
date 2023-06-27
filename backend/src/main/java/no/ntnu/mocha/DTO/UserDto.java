package no.ntnu.mocha.DTO;

/**
 * <h1>User DTO (Data Transfer Object)</h1>
 * 
 * <p>Representing an Data Transfer Object class for
 * User so that we can share either required data
 * or complete data from the source.</p>
 * 
 * @version 22.03.2023
 * @since   22.03.2023
 */
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String bio;
    private String role;

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getBio() { return bio; }
    public String getRole() { return role; }
}
