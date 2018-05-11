package com.juver.test1;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import database.EventRepository;
import database.Events_event;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

@SpringBootApplication
@EnableJpaRepositories("database")
@EntityScan("database")
public class DataBaseTests {
	private static final Logger log = LoggerFactory.getLogger(DataBaseTests.class);

	@Test
	public void QueryPostgressDb() {
		log.info("Arrange");
		int eventId = 19;
		ConfigurableApplicationContext context = SpringApplication.run(DataBaseTests.class);
		EventRepository repository = context.getBean(EventRepository.class);
		Events_event event = repository.findById(eventId).orElse(null);
		log.info("Act-Assert");
		given()
			.pathParam("eventId", eventId)
		.when()
			.get("https://bxevents.herokuapp.com:443/event/{eventId}/")
		.then()
			.assertThat().statusCode(200).and()
			.assertThat().body("title", equalTo(event.getTitle()));
	}
}
