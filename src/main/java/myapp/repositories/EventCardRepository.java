package myapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import myapp.models.EventCard;

public interface EventCardRepository extends CrudRepository<EventCard, Integer>{
	
	@Query("SELECT br FROM EventCard br WHERE br.publisherId = :publisherId")
	List<EventCard> findAllEventCardForPublisher(@Param("publisherId") String publisherId);


}
