package no.ntnu.mocha.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import no.ntnu.mocha.domain.entities.Image;

/**
 * Represents ImageRepository which extends
 * CrudRepsitory for CRUD-functionalites.
 * 
 * @version 21.03.2023
 * @since   21.03.2023
 * @see     Image
 */
@RepositoryRestResource
public interface ImageRepository extends CrudRepository<Image, Long>{}
