package no.ntnu.mocha.service.endpoints;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import no.ntnu.mocha.DTO.UserDto;
import no.ntnu.mocha.domain.entities.Order;
import no.ntnu.mocha.domain.entities.Role;
import no.ntnu.mocha.domain.entities.User;
import no.ntnu.mocha.domain.repository.CartItemRepository;
import no.ntnu.mocha.domain.repository.OrderRepository;
import no.ntnu.mocha.domain.repository.RoleRepository;
import no.ntnu.mocha.domain.repository.UserRepository;
import no.ntnu.mocha.service.email.EmailService;


/**
 * <Business Logic Service for the User</h1>
 * 
 * Representing an Review class for the User.
 * 
 * @version 29.04.2023
 * @since   25.04.2023
 */
@Service public class UserService {
    
    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private EmailService emailService;
    @Autowired private OrderRepository orderRepository;
    @Autowired private CartItemRepository cartItemRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    

    /**
     * Add a new user to the database.
     * 
     * @param dto the dto representing the user.
     * @return the new user.
     */
    public User addUser(UserDto dto) {
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findOneByName("ROLE_USER"));

        User user = new User(
            dto.getUsername(),
            bCryptPasswordEncoder.encode(dto.getPassword()),
            roles,
            dto.getEmail(),
            dto.getBio()
        );

        emailService.sendAccountConfirmation(dto.getEmail());

        return userRepository.save(user);
    }


    /**
     * Update an existing user.
     * 
     * @param id the id of the user.
     * @param dto the dto representing the user.
     */
    public void updateUser(long id, UserDto dto) {
        userRepository.updateUser(
            id, 
            bCryptPasswordEncoder.encode(dto.getPassword()), 
            dto.getEmail(), 
            dto.getBio()
        );
    }


    /**
     * Delete a user from the database.
     * 
     * @param id the id of the user.
     * @return true if user was deleted, false otherwise.
     */
    public boolean deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User u = user.get();
            List<Order> orders = orderRepository.findAllByUserId(u.getId());
            for (Order order : orders) {
                cartItemRepository.deleteAllCartItemByOrderId(order.getId());
                orderRepository.delete(order);
            }
            userRepository.deleteAllUserRoles(u.getId());
            userRepository.delete(u);
        }
        return user.isPresent();
    }


    /**
     * Checks if the user attempting to perform something on a user entity
     * actually is the user itself.
     * 
     * @param id the id of the user to manipulate.
     * @return true if the clients username is the same as username of the 
     *      user with the given id, otherwise false.
     */
    public boolean validateUserAction(long id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            return false;
        }
        String subjectUsername = user.get().getUsername();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<String> roles = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getAuthorities()
            .stream()
            .map(a -> a.getAuthority())
            .collect(Collectors.toList());

        return (subjectUsername.equals(username) || roles.contains("ROLE_ADMIN"));
    }


    /**
     * Finds the ID of a user by its username.
     * 
     * @param username the username of a user.
     * @return the ID of the user or -1 if not found.
     */
    public long getUserId(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return (user.isPresent()) ? user.get().getId() : -1;
    }
}
