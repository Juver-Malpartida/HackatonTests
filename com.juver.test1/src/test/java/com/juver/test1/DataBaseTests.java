package com.juver.test1;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import database.Customer;
import database.CustomerRepository;
import database.EventRepository;
import database.Events_event;

import static org.hamcrest.Matchers.*;

import java.util.Optional;

import static io.restassured.RestAssured.*;

@SpringBootApplication
@ComponentScan(basePackages="database")
public class DataBaseTests {
	private static final Logger log = LoggerFactory.getLogger(DataBaseTests.class);
	@Test
	public void QueryDb() {
		ConfigurableApplicationContext context = SpringApplication.run(DataBaseTests.class);
		CustomerRepository repository = context.getBean(CustomerRepository.class);
		repository.save(new Customer("Jack", "Bauer"));
		repository.save(new Customer("Chloe", "O'Brian"));
		repository.save(new Customer("Kim", "Bauer"));
		repository.save(new Customer("David", "Palmer"));
		repository.save(new Customer("Michelle", "Dessler"));

		// fetch all customers
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Customer customer : repository.findAll()) {
			log.info(customer.toString());
		}
		log.info("");

		// fetch an individual customer by ID
		repository.findById(1L)
			.ifPresent(customer -> {
				log.info("Customer found with findById(1L):");
				log.info("--------------------------------");
				log.info(customer.toString());
				log.info("");
			});

		// fetch customers by last name
		log.info("Customer found with findByLastName('Bauer'):");
		log.info("--------------------------------------------");
		repository.findByLastName("Bauer").forEach(bauer -> {
			log.info(bauer.toString());
		});
		// for (Customer bauer : repository.findByLastName("Bauer")) {
		// 	log.info(bauer.toString());
		// }
		log.info("");
		
		// Act-Assert
		given().
		when().
			get("https://bxevents.herokuapp.com:443/event/19/").
		then().
			assertThat().statusCode(200).
			and().
			assertThat().body("title", equalTo("Hackatrix Lima 2018"));
	}
	
	@Test
	public void QueryPostgressDb() {
		int eventId = 19;
		ConfigurableApplicationContext context = SpringApplication.run(DataBaseTests.class);
		EventRepository repository = context.getBean(EventRepository.class);
		Events_event event = repository.findById(eventId).orElse(null);
	}
}
