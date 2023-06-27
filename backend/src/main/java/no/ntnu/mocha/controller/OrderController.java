package no.ntnu.mocha.controller;

import java.util.List;

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
import no.ntnu.mocha.DTO.CartItemDto;
import no.ntnu.mocha.DTO.OrderDto;
import no.ntnu.mocha.domain.entities.Order;
import no.ntnu.mocha.service.endpoints.OrderService;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired private OrderService service;


    @GetMapping
    @Operation(
        summary = "Get all orders",
        description = "Returns a collection of all orders."
    )
    public Iterable<Order> getOrders() {
        return service.getAllOrders();
    }


    @PostMapping
    @Operation(
        summary = "Create new order",
        description = "Create a new order entity."
    )
    public ResponseEntity<?> addOrder(@Parameter(description = "DTO representing an order entity.") @RequestBody OrderDto dto) {
        return (service.createOrder(dto) != null) ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/user/{id}")
    @Operation(
        summary = "Get all users orders",
        description = "Returns a collection of all orders made by a user."
    )
    public Iterable<Order> getByUser(@Parameter(description = "ID of the user.") @PathVariable long id) {
        return service.getAllByUser(id);
    }


    @PutMapping("/{id}")
    @Operation(
        summary = "Update order",
        description = "Update an existing order."
    )
    public ResponseEntity<?> updateOrder(
        @Parameter(description = "ID of the order.") @PathVariable long id, 
        @Parameter(description = "DTO represending an order entity.") @RequestBody OrderDto dto) 
    {
        service.update(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete order",
        description = "Delete an order from the database."
    )
    public ResponseEntity<?> deleteOrder(@Parameter(description = "ID of the order.") @PathVariable long id) {
        service.deleteOrder(id); 
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/user/{id}")
    @Operation(
        summary = "Submit a new order",
        description = "Collect a set of cart-items into a single order and submit it."
    )
    public ResponseEntity<?> submitOrder(
        @Parameter(description = "ID of the user.") @PathVariable long id, 
        @Parameter(description = "List of cart-items DTOs.") @RequestBody List<CartItemDto> items) 
    {
        return (service.submitOrder(id, items)) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
} 
