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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import no.ntnu.mocha.DTO.ProductDto;
import no.ntnu.mocha.domain.entities.Product;
import no.ntnu.mocha.domain.entities.Review;
import no.ntnu.mocha.service.endpoints.ProductService;

/**
 * Serves product-related REST API endpoints.
 */
@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
    
    @Autowired private ProductService service;


    /**
     * Get all products stored in the database with display=true.
     * 
     * @return  All products in the storage.
     * @throws InterruptedException An exception if the executing thread is interrupted while
     *                              it is sleeping. 
     */
    @GetMapping
    @Operation(
        summary = "Get all products",
        description = "Returns a collection of all products to display."
    )
    public Iterable<Product> getAll() {
        return service.getAllProducts();
    }


    /**
     * Get all products stored in the database.
     * 
     * @return  All products in the storage.
     * @throws InterruptedException An exception if the executing thread is interrupted while
     *                              it is sleeping. 
     */
    @GetMapping("/admin")
    @Operation(
        summary = "Get all products",
        description = "Returns a collection of all products."
    )
    public Iterable<Product> getAllAsAdmin() {
        return service.getAll();
    }



    @GetMapping("/order")
    @Operation(
        summary = "Get all products by order",
        description = "Returns all products ordered by specfified parameter."
    )
    public Iterable<Product> getAllOrderedBy(@Parameter(description = "Order scheme (PRICE or POPULARITY).") @RequestParam String order) {
        return service.getAllOrderedBy(order);
    }


    @GetMapping("/name")
    @Operation(
        summary = "Get product by name",
        description = "Returns a product by a given name."
    )
    public Product getByName(@Parameter(description = "Name of the product.") @RequestParam String name) {
        return service.getProductByName(name);
    }


    @GetMapping("/category")
    @Operation(
        summary = "Get products of category",
        description = "Returns all product of a given category."
    )
    public Iterable<Product> getByCategory(@Parameter(description = "Category name.") @RequestParam String category) {
        return service.getAllByCategory(category);
    }


    @GetMapping("/{id}") 
    @Operation(
        summary = "Get product by product ID",
        description = "Returns a product with a given ID."
    )
    public ResponseEntity<?> getProductById(@Parameter(description = "Product ID.") @PathVariable long id) {
        return (service.getProduct(id) != null) ? 
            ResponseEntity.ok(service.getProduct(id)) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/{id}/reviews")
    @Operation(
        summary = "Get product reviews.",
        description = "Get all reviews of a given product."
    )
    public Iterable<Review> getReviews(@Parameter(description = "Product ID.") @PathVariable long id) {
        return service.getReviews(id);
    }


    @PostMapping
    @Operation(
        summary = "Add product",
        description = "Add a new product to the database."
    )
    public ResponseEntity<?> addProduct(@Parameter(description = "DTO representing a product entity.") @RequestBody ProductDto dto) {
        service.addProduct(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Operation(
        summary = "Update product",
        description = "Update a given product."
    )
    public ResponseEntity<?> updateProductById(
        @Parameter(description = "Product ID.") @PathVariable long id, 
        @Parameter(description = "DTO representing a product entity.") @RequestBody ProductDto dto) 
    {
        service.updateProduct(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete product",
        description = "Delete a given product."
    )
    public ResponseEntity<?> deleteProduct(@Parameter(description = "Product ID.") @PathVariable long id) {
        return service.deleteProduct(id) ? 
            new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}/increment")
    @Operation(
        summary = "Increment product purchases",
        description = "Increment the number of purchases of a given product."
    )
    public ResponseEntity<?> incrementProductPurchases(@Parameter(description = "Product ID.") @PathVariable long id) {
        service.increment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/{id}/setprice")
    @Operation(
        summary = "Update product price",
        description = "Update the price of a given product."
    )
    public ResponseEntity<?> updateProductPrice(
        @Parameter(description = "Product ID.") @PathVariable long id, 
        @Parameter(description = "New price.")  @RequestParam double price) 
    {
        service.updatePrice(id, price);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
