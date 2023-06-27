package no.ntnu.mocha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import no.ntnu.mocha.DTO.UserDto;
import no.ntnu.mocha.service.authentication.JwtService;
import no.ntnu.mocha.service.endpoints.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    
    @Autowired private UserService service;
    @Autowired private JwtService jwtService;



    @PostMapping
    @Operation(
        summary = "Add a new user",
        description = "Create a new user with given credentials and append it to the database."
    )
    public ResponseEntity<?> addUser(@Parameter(description = "Credentials of the new user.") @RequestBody UserDto dto) {
        service.addUser(dto);

        String jwts = jwtService.getToken(dto.getUsername(), dto.getPassword());

        return ResponseEntity.created(null)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
            .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
            .build();
    }


    @GetMapping("/{username}")
    @Operation(
        summary = "Get user ID",
        description = "Get the user ID of a given user by searching the username."
    )
    public ResponseEntity<?> getIdByUsername(@Parameter(description = "Username of the user.") @PathVariable String username) {
        long id = service.getUserId(username);
        return (id == -1) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok().body(id);
    }


    @PutMapping("/{id}")
    @Operation(
        summary = "Update a user",
        description = "Update the credentials of an existing user."
    )
    public ResponseEntity<?> updateUser(
        @Parameter(description = "Id property of the given user.") @PathVariable long id, 
        @Parameter(description = "Credentials of the given user.") @RequestBody UserDto dto) 
    {

        if (service.validateUserAction(id)) {
            service.updateUser(id, dto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to modify this user.");
        }
    }


    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a user",
        description = "Delete a given user from the database."
    )
    public ResponseEntity<?> deleteUser(
        @Parameter(description = "Id property of the user to delete.") @PathVariable long id) 
    {
        if (service.validateUserAction(id)) {
            return service.deleteUser(id) ? 
                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to modify this user.");
        }
    }
}
