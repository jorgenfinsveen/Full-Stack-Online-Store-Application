package no.ntnu.mocha;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import no.ntnu.mocha.controller.CartItemController;
import no.ntnu.mocha.controller.LoginController;
import no.ntnu.mocha.controller.OrderController;
import no.ntnu.mocha.controller.ProductController;
import no.ntnu.mocha.controller.ReviewController;
import no.ntnu.mocha.controller.UserController;


/**
 * Test-class responsible for testing things related to
 * the REST-endpoints.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ControllerTest {
    
    @Autowired UserController userController;
    @Autowired ReviewController reviewController;
    @Autowired ProductController productController;
    @Autowired OrderController orderController;
    @Autowired LoginController loginController;
    @Autowired CartItemController cartItemController;


    /**
     * Asserts that each controller has been initialized.
     */
    @Test
    public void controllers_are_reacheable() {
        assertNotNull(userController);
        assertNotNull(reviewController);
        assertNotNull(productController);
        assertNotNull(orderController);
        assertNotNull(loginController);
        assertNotNull(cartItemController);
    }
}
