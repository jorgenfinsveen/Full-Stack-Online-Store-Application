package no.ntnu.mocha.service.endpoints;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.mocha.DTO.ProductDto;
import no.ntnu.mocha.domain.entities.Image;
import no.ntnu.mocha.domain.entities.Product;
import no.ntnu.mocha.domain.entities.ProductCategory;
import no.ntnu.mocha.domain.entities.Review;
import no.ntnu.mocha.domain.repository.ImageRepository;
import no.ntnu.mocha.domain.repository.ProductCategoryRepository;
import no.ntnu.mocha.domain.repository.ProductRepository;
import no.ntnu.mocha.domain.repository.ReviewRepository;


/**
 * <Business Logic Service for the Product</h1>
 * 
 * Representing an Service class for the Product.
 * 
 * @version 21.04.2023
 * @since   21.04.2023
 */
@Service public class ProductService {

    @Autowired private ProductRepository productRepository;
    @Autowired private ImageRepository imageRepository;
    @Autowired private ProductCategoryRepository categoryRepository;
    @Autowired private ReviewRepository reviewRepository;



    /**
     * Returns an Iterable of all products in the database ordered
     * by product ID where display=true.
     * 
     * @return an Iterable of all products in the database where display=true.
     */
    public Iterable<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        productRepository.getAllToDisplay().forEach(productList::add);

        return productList.stream()
            .sorted(Comparator.comparingLong(Product::getId))
            .collect(Collectors.toList());
    }


    /**
     * Returns an Iterable of all products in the database ordered
     * by product ID.
     * 
     * @return an Iterable of all products in the database.
     */
    public Iterable<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);

        return productList.stream()
            .sorted(Comparator.comparingLong(Product::getId))
            .collect(Collectors.toList());
    }


    /**
     * Returns an Iterable of all products ordered either by
     * price or times purchased in descending order.
     * 
     * @param order property to order in.
     * @return Iterable of the products in given order.
     */
    public Iterable<Product> getAllOrderedBy(String order) {
        switch(order.toUpperCase()) {
            case "PRICE": return productRepository.getAllOrderedByPrice();
            case "POPULARITY": return productRepository.getAllOrderedByPurchases();
            default: return getAllProducts();
        } 
    }


    /**
     * Get all reviews of a given product.
     * 
     * @param id the id of the product.
     * @return Iterable of reviews.
     */
    public Iterable<Review> getReviews(long id) {
        return reviewRepository.getAllByProductId(id);
    }


    /**
     * Get a specific product from its ID.
     * 
     * @param id the ID of the product.
     * @return the product with the given ID.
     */
    public Product getProduct(long id) {
        Optional<Product> product = productRepository.findById(id);
        return (product.isPresent()) ? product.get() : null;
    }


    /**
     * Get a specific product from its name.
     * 
     * @param name the name of the product.
     * @return the product with the given name.
     */
    public Product getProductByName(String name) {
        return productRepository.findByName(name.substring(0, 1).toUpperCase() + name.substring(1));
    }


    /**
     * Get all products of a given category.
     * 
     * @param category the name of the category.
     * @return Iterable of all products of the given category.
     */
    public Iterable<Product> getAllByCategory(String category) {
        Optional<ProductCategory> cat = categoryRepository.findByName(category.toUpperCase());
        return (cat.isPresent()) ? productRepository.getAllByCategory(cat.get().getId()) : null;
    }


    /**
     * Add a new product to the database.
     * 
     * @param dto the dto representing the product.
     * @return the new product or null if error occured.
     */
    public Product addProduct(ProductDto dto) {
        Optional<Image> image = imageRepository.findById(dto.getImageId());
        Optional<ProductCategory> category = categoryRepository.findByName(dto.getCategory());

        if (image.isPresent() && category.isPresent()) {
            Product product = new Product(
                dto.getName(),
                dto.getPrice(),
                dto.getAmount(),
                dto.getDescription(),
                dto.getDisplay(),
                image.get(),
                category.get()
            );
            return productRepository.save(product);
        } else { return null; }
    }


    /** 
     * Update an existing product.
     * 
     * @param id the ID of the product.
     * @param dto the dto representing the product.
    */
    public void updateProduct(long id, ProductDto dto) {
        Optional<ProductCategory> category = categoryRepository.findByName(dto.getCategory());

        if (category.isPresent()) {
            productRepository.updateProduct(
                id, 
                dto.getDisplay(), 
                dto.getPrice(), 
                dto.getImageId(), 
                category.get().getId(), 
                dto.getAmount(), 
                dto.getDescription(), 
                dto.getName(),
                dto.getTotalBought()
            );
        }
    }


    /**
     * Increment the number of purchases of the product.
     * 
     * @param id the id of the product.
     */
    public void increment(long id) {
        productRepository.increment(id);
    }


    /**
     * Update the price of the product.
     * 
     * @param id the id of the product.
     * @param price the new price.
     */
    public void updatePrice(long id, double price) {
        productRepository.updatePrice(id, price);
    }


    /**
     * Delete a product from the database.
     * 
     * @param id the id of the product.
     * @return true if deleted, otherwise false.
     */
    public boolean deleteProduct(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) productRepository.delete(product.get());
        return product.isPresent();
    }
}
