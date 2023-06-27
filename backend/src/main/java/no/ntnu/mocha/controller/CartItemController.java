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
import no.ntnu.mocha.DTO.CartItemDto;
import no.ntnu.mocha.domain.entities.CartItem;
import no.ntnu.mocha.domain.entities.Product;
import no.ntnu.mocha.service.endpoints.CartItemService;

/**
 * <h1>Cart Item Controller </h1>
 * 
 * <p> REST API controller for the Cart Item.
 * 
 * @version 22.04.2023
 * @since   22.04.2023
 */
@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartItemController {

    @Autowired private CartItemService service;


    @GetMapping
    @Operation(
        summary = "Get cart-items of an order",
        description = "Returns a collection of all cart-items assigned to an order."
    )
    public Iterable<CartItem> getCart(@Parameter(description = "ID of the order.") @RequestParam long orderId) {
        return service.getCart(orderId);
    }


    @GetMapping("/products")
    @Operation(
        summary = "Get the products of an order",
        description = "Returns a collection of all products assigned to an order."
    )
    public Iterable<Product> getCartProducts(@Parameter(description = "ID of the order.") @RequestParam long orderId) {
        return service.getCartProducts(orderId);
    }


    @DeleteMapping
    @Operation(
        summary = "Clear cart",
        description = "Deletes all cart-items of a given order."
    )
    public ResponseEntity<?> deleteCart(@Parameter(description = "ID of the order.") @RequestParam long orderId) {
        service.deleteAllCartItemById(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete cart-item",
        description = "Deletes a given cart-item of a given order."
    )
    public ResponseEntity<?> deleteCartItem(@Parameter(description = "ID of the cart-item.") @PathVariable long id) {
        service.deleteCartItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/decrement/{id}")
    @Operation(
        summary = "Decrement cart-item amount",
        description = "Decrements the number of a given cart-item."
    )
    public ResponseEntity<?> decrementCartItem(@Parameter(description = "ID of the cart-item.") @PathVariable long id) {
        service.decrementCartItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/increment/{id}")
    @Operation(
        summary = "Increment cart-item amount",
        description = "Increments the number of a given cart-item."
    )
    public ResponseEntity<?> incrementCartItem(@Parameter(description = "ID of the cart-item.") @PathVariable long id) {
        service.incrementCartItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping
    @Operation(
        summary = "Add cart-item.",
        description = "Add a new cart-item."
    )
    public ResponseEntity<?> addCartItem(@Parameter(description = "DTO representing a cart-item entity.") @RequestBody CartItemDto dto) {
        return (service.addCartItem(dto) != null) ? 
            new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
 