package no.ntnu.mocha.DTO;

/**
 * Data that the user will send in the login request.
 */
public class CredentialsDto {
    
  private String username;
  private String password;

  public String getUsername() { return username; }
  public String getPassword() { return password; }
}
