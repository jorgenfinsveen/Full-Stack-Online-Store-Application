package no.ntnu.mocha.service.email;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import no.ntnu.mocha.domain.entities.CartItem;
import no.ntnu.mocha.domain.entities.Order;
import no.ntnu.mocha.domain.entities.Product;
import no.ntnu.mocha.domain.repository.CartItemRepository;



/**
 * Represents an email-handler which is responsible for 
 * sending emails to a user upon signing up with a given
 * email address.
 */
@Service public class EmailService {
    
    /** Senders email-address. */
    @Value("${spring.mail.username}") private String sender;
    @Autowired private JavaMailSender emailClient;
    @Autowired private CartItemRepository cartItemRepository;


    /**
     * Sends an email welcoming a new user to the Mocha customer
     * zone.
     * 
     * @param receiver The email-address of the person to send the mail to.
     */
    public void sendAccountConfirmation(String receiver) {
        try {
            MimeMessage message = emailClient.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject("Account confirmation");
            helper.setText("Thank you for creating your account at Mocha!");

            emailClient.send(message);
        } catch (Exception e) { e.printStackTrace(); }
    }


    /**
     * Sends an email with order confirmation.
     * 
     * @param receiver The email-address of the person to send the mail to.
     * @param order The order which was made.
     */
    public void sendOrderConfirmation(String receiver, Order order) {
        List<CartItem> items = cartItemRepository.getAllByOrderId(order.getId());
        ArrayList<String> productNames = new ArrayList<>();
        ArrayList<Integer> productAmount = new ArrayList<>();

        for (CartItem item : items) {
            Product product = item.getProduct();
            productNames.add(product.getName());
            productAmount.add(item.getAmount());
        }

        String list = "";

        for (int i = 0; i < productNames.size(); i++) {
            list += "- " + productAmount.get(i) + "x " + productNames.get(i) + "\n";
        }


        try {
            MimeMessage message = emailClient.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject("Order confirmation");
            
            String text = "Thank you for shopping with us. This is what you bought on " + order.getDate().toString() + ":\n\n";
            text += list + "\n\nEnjoy your products! \n\nKind regards, \nMocha<3";

            helper.setText(text);

            emailClient.send(message);
        } catch (Exception e) { e.printStackTrace(); }
    }
}
