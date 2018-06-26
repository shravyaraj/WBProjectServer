package myapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import myapp.models.BookReviews;
import myapp.models.Event;

public interface EventRepository extends CrudRepository<Event, Integer>{

	@Query("SELECT br FROM Event br WHERE br.publisherId = :publisherId")
	List<Event> findAllEventsForPublisher(@Param("publisherId") String publisherId);

}
