package database;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends 
CrudRepository<Events_event, Integer>{
	Events_event findByIsUpcomingIsTrue();
}
