package no.ntnu.mocha;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import no.ntnu.mocha.domain.entities.Role;
import no.ntnu.mocha.domain.repository.RoleRepository;

/**
 * <h1>Mocha Application</h1>
 * 
 * <p>Represents the main class for the 
 * Spring Boot Application "Mocha Application" with the 
 * CommandLineRunner interface.</p>
 * 
 * @version	29.05.2023
 * @since	21.03.2023
 */
@SpringBootApplication
public class MochaApplication implements CommandLineRunner {

	/** Logger object used for loggin application information. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MochaApplication.class);

	@Autowired private RoleRepository repository;
	@Autowired private Environment environment;


	

	/**
	 * Starting point of the Spring Boot application. Initializes the rest of the
	 * application components.
	 * 
	 * @param args arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(MochaApplication.class, args);
	}



	/**
	 * Logs the IP-address and port-number in which the server is running on and
	 * ensures that the required ROLE entities are present in the database.
	 * 
	 * @param args arguments.
	 */
	@Override
	public void run(String... args) throws Exception {

		/* Logs active IP-address and port to STDOUT. */
		LOGGER.info(
			"Server instance initialized: " 
			+ InetAddress.getLocalHost().getHostAddress() + ":" 
			+ environment.getProperty("local.server.port")
		);

		/* Ensures that the two ROLE instances are present in the database. */
		if (repository.findOneByName("ROLE_USER") == null) repository.save(new Role("ROLE_USER"));
		if (repository.findOneByName("ROLE_ADMIN") == null) repository.save(new Role("ROLE_ADMIN"));
	}
}
