package no.ntnu.mocha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import no.ntnu.mocha.DTO.ReviewDto;
import no.ntnu.mocha.domain.entities.Review;
import no.ntnu.mocha.service.endpoints.ReviewService;


@RestController
@RequestMapping("/reviews")
@CrossOrigin
public class ReviewController {
    
    @Autowired private ReviewService service;


    @GetMapping
    @Operation(
        summary = "Get all reviews.",
        description = "Get all reviews in the database."
    )
    public Iterable<Review> getAll() {
        return service.getAll();
    }


    @PostMapping
    @Operation(
        summary = "Add a product review.",
        description = "Add a new review to a given product."
    )
    public ResponseEntity<?> addReview(@Parameter(description = "DTO representing a review entity.") @RequestBody ReviewDto dto) {
        return (service.addReview(dto) != null) ? 
            new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}")
    @Operation(
        summary = "Update a review.",
        description = "Update a given review."
    )
    public ResponseEntity<?> updateReview(
        @Parameter(description = "Review ID.") @PathVariable long id, 
        @Parameter(description = "DTO representing a review entity.") @RequestBody ReviewDto dto) 
    {
        service.updateReview(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a product review.",
        description = "Delete a new review to a given product."
    )
    public ResponseEntity<?> deleteReview(@Parameter(description = "Review ID.") @PathVariable long id) {
        service.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
