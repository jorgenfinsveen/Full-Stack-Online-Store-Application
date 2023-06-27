package no.ntnu.mocha.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import no.ntnu.mocha.domain.entities.Review;

/**
 * Represents ReviewRepository which extends
 * CrudRepsitory for CRUD-functionalites.
 * 
 * @version 21.03.2023
 * @since   21.03.2023
 * @see     Review
 */
@RepositoryRestResource
public interface ReviewRepository extends CrudRepository<Review, Long> {
    
    @Query(value = "select * from review r where r.product_id = ?1", nativeQuery = true)
    List<Review> getAllByProductId(long id);


    @Transactional
    @Modifying
    @Query(value = "update review r set r.user_id = ?2, r.product_id = ?3, r.review_comment = ?4, r.stars = ?6, r.review_date = ?5 where r.review_id = ?1", nativeQuery = true)
    void updateReview(long id, long uId, long pId, String comment, LocalDate date, int stars);
}
