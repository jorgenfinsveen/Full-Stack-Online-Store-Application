package no.ntnu.mocha;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import no.ntnu.mocha.domain.entities.User;
import no.ntnu.mocha.domain.repository.UserRepository;


/**
 * Test-class responsible for testing things related to
 * the CRUD-repositories.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RepositoryTest {
    
    @Autowired UserRepository userRepository;

    private User testUser = new User(
        "JUnit-user", 
        "password", 
        null, 
        "this is a test user", 
        "mail@domain.com"
    );


    
    /**
     * Asserts that the repository has been initialized.
     */
    @Test
    public void repositories_are_reachable() {
        assertNotNull(userRepository);
    }


    /** 
     * Tries to save a new entity into the user repository. Entity
     * should be present in the repository after Arrange-Act.
    */
    @Test
    public void saving_entity_to_repository() {
        userRepository.save(testUser);

        assertTrue(userRepository.findByUsername(testUser.getUsername()).isPresent());
    }


    /**
     * Tries to delete the user from the repository.
     */
    @After
    public void delete_entity() {
        userRepository.delete(testUser);
    }


    /**
     * Asserts that the entity has been deleted by confirming its abscence
     * in the repository.
     */
    @Test
    public void entity_was_deleted_from_repository() {
        assertFalse(userRepository.findByUsername(testUser.getUsername()).isPresent());
    }


    /**
     * Asserts that passwords in the database has been encrypted, and that the
     * encryption algorithm is as expected, starting with the phrase "$2a$10$".
     */
    @Test
    public void passwords_are_encrypted() {
        Optional<User> admin = userRepository.findByUsername("admin");
        
        assertTrue(admin.isPresent());
        assertNotEquals("password", admin.get().getPassword());
        assertTrue(admin.get().getPassword().startsWith("$2a$10$"));
    }
}
