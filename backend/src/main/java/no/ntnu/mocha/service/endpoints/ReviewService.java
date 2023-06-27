package no.ntnu.mocha.service.endpoints;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.mocha.DTO.ReviewDto;
import no.ntnu.mocha.domain.entities.Product;
import no.ntnu.mocha.domain.entities.Review;
import no.ntnu.mocha.domain.entities.User;
import no.ntnu.mocha.domain.repository.ProductRepository;
import no.ntnu.mocha.domain.repository.ReviewRepository;
import no.ntnu.mocha.domain.repository.UserRepository;


/**
 * <Business Logic Service for the Review</h1>
 * 
 * Representing an Review class for the Product.
 * 
 * @version 29.04.2023
 * @since   25.04.2023
 */
@Service public class ReviewService {
    
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ProductRepository productRepository;


    /**
     * Get all reviews in the database.
     * 
     * @return iterable of all reviews.
     */
    public Iterable<Review> getAll() {
        return reviewRepository.findAll();
    }


    /**
     * Add a new review to the database.
     * 
     * @param dto the dto representing the review.
     * @return the new review entity or null if error occured.
     */
    public Review addReview(ReviewDto dto) {
        Optional<User> user = userRepository.findById(dto.getUserId());
        Optional<Product> product = productRepository.findById(dto.getProductId());

        if (!user.isPresent() || !product.isPresent()) return null;

        Review review = new Review(
            user.get(), 
            product.get(), 
            dto.getComment(), 
            dto.getStars()
        );

        return reviewRepository.save(review);
    }


    /**
     * Update an existing review.
     * 
     * @param id the id of the review.
     * @param dto the dto representing the review.
     */
    public void updateReview(long id, ReviewDto dto) {
        reviewRepository.updateReview(
            id, 
            dto.getUserId(), 
            dto.getProductId(), 
            dto.getComment(), 
            dto.getDate(), 
            dto.getStars()
        );
    }


    /**
     * Delete a review from the database.
     * 
     * @param id the id of the review.
     */
    public void deleteReview(long id) {
        reviewRepository.deleteById(id);
    }
}
