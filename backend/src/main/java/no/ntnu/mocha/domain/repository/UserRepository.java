package no.ntnu.mocha.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import no.ntnu.mocha.domain.entities.User;

/**
 * Represents UserRepository which extends
 * CrudRepsitory for CRUD-functionalites.
 * 
 * @version 21.03.2023
 * @since   21.03.2023
 * @see     User
 */
@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "update user u set u.password = ?2, u.email = ?3, u.bio = ?4 where u.user_id = ?1", nativeQuery = true)
    void updateUser(long id, String password, String email, String bio);

    @Transactional
    @Modifying
    @Query(value = "delete from user_roles where user_id = ?1", nativeQuery = true)
    void deleteAllUserRoles(long id);
}
