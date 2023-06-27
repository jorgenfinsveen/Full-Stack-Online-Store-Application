package no.ntnu.mocha.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.ntnu.mocha.domain.entities.Role;

@Repository("roleJpaRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findOneByName(String name);
}
