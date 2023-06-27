package no.ntnu.mocha;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


/**
 * Test class responsible for testing that each endpoint is configured
 * to only be accessible when having an appropriate authentication in
 * the JWT. The JWT contains the roles of the user signing in, which
 * determines whether it can access the different endpoints.</p>
 * 
 * <b>Endpoints</b>:
 * <ul>
 *  <li> Public </li>
 *  <li> User </li>
 *  <li> Admin </li>
 * </ul>
 * 
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class EndpointFilterTest {
    
    @Autowired private MockMvc mvc;

    private static final byte[] ADMIN_CREDENTIALS = "{\"username\":\"admin\",\"password\":\"admin\"}".getBytes();
    private static final byte[] USER_CREDENTIALS  = "{\"username\":\"user10\",\"password\":\"user10\"}".getBytes();


    /**
     * Returns a JWT token containing either the USER role or both USER and ADMIN roles.
     */
    private String getToken(byte[] credentials) throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
			.post("/login")
			.content(credentials)
			.contentType("application/json");
		
		return mvc.perform(builder)
			.andReturn()
            .getResponse()
            .getHeader(HttpHeaders.AUTHORIZATION);
    }



    /**
     * Asserts that a public-endpoint can be reached without authentication. A
     * JWT is not required, and the response should have the HTTP Status Code 200.
     */
    @Test
    public void public_endpoint_is_reachable_without_authentication() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
			.get("/products")
			.contentType("application/json");

        mvc.perform(builder)
            .andDo(print())
            .andExpect(status().isOk());
    }


    /**
     * Asserts that a user-endpoint cannot be reached without authentication. A
     * JWT is required, and the response should have the HTTP Status Code 401.
     */
    @Test
    public void user_endpoint_is_not_reachable_without_authentication() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
			.get("/cart/1")
			.contentType("application/json");

        mvc.perform(builder)
            .andDo(print())
            .andExpect(status().isUnauthorized());
    }


    /**
     * Asserts that a user-endpoint can be reached with USER authentication. A
     * JWT is required, and the response should have the HTTP Status Code 200.
     */
    @Test
    public void user_endpoint_is_reachable_with_user_authentication() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
			.get("/orders/user/3")
            .header(HttpHeaders.AUTHORIZATION, getToken(USER_CREDENTIALS))
			.contentType("application/json");

        mvc.perform(builder)
            .andDo(print())
            .andExpect(status().isOk());
    }


    /**
     * Asserts that a user-endpoint can be reached with ADMIN authentication. A
     * JWT is required, and the response should have the HTTP Status Code 200.
     */
    @Test
    public void user_endpoint_is_reachable_with_admin_authentication() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
			.get("/orders/user/3")
            .header(HttpHeaders.AUTHORIZATION, getToken(ADMIN_CREDENTIALS))
			.contentType("application/json");

        mvc.perform(builder)
            .andDo(print())
            .andExpect(status().isOk());
    }


    /**
     * Asserts that an admin-endpoint cannot be reached without authentication. A
     * JWT is required, and the response should have the HTTP Status Code 401.
     */
    @Test
    public void admin_endpoint_is_not_reachable_without_authentication() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
			.get("/orders")
			.contentType("application/json");

        mvc.perform(builder)
            .andDo(print())
            .andExpect(status().isUnauthorized());
    }


    /**
     * Asserts that an admin-endpoint cannot be reached with USER authentication. A
     * JWT is required, and the response should have the HTTP Status Code 403.
     */
    @Test
    public void admin_endpoint_is_reachable_with_user_authentication() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
			.get("/orders")
            .header(HttpHeaders.AUTHORIZATION, getToken(USER_CREDENTIALS))
			.contentType("application/json");

        mvc.perform(builder)
            .andDo(print())
            .andExpect(status().isForbidden());
    }


    /**
     * Asserts that an admin-endpoint can be reached with ADMIN authentication. A
     * JWT is required, and the response should have the HTTP Status Code 200.
     */
    @Test
    public void admin_endpoint_is_reachable_with_admin_authentication() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
			.get("/orders")
            .header(HttpHeaders.AUTHORIZATION, getToken(ADMIN_CREDENTIALS))
			.contentType("application/json");

        mvc.perform(builder)
            .andDo(print())
            .andExpect(status().isOk());
    }
}
