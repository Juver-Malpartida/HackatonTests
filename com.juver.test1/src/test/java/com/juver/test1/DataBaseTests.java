package com.juver.test1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import database.EventRepository;
import database.Events_event;

@SpringBootApplication
@EnableJpaRepositories("database")
@EntityScan("database")
@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource("classpath:application-test.properties")
public class DataBaseTests {
	
	private static final Logger log = LoggerFactory.getLogger(DataBaseTests.class);
	
	@Autowired
	private EventRepository eventRepository;

	@Test
	public void QueryPostgressDb() {
		log.info("Arrange");
		Events_event event = eventRepository.findByIsUpcomingIsTrue();
		
		log.info("Act-Assert");
		given()
			.pathParam("eventId", event.getId())
		.when()
			.get("https://bxevents.herokuapp.com:443/event/{eventId}/")
		.then()
			.assertThat().statusCode(200).and()
			.assertThat().body("title", equalTo(event.getTitle()));
	}
}
