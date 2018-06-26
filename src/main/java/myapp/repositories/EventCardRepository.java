package myapp.repositories;

import org.springframework.data.repository.CrudRepository;

import myapp.models.EventCard;

public interface EventCardRepository extends CrudRepository<EventCard, Integer>{

}
