package myapp.repositories;

import org.springframework.data.repository.CrudRepository;

import myapp.models.Event;

public interface EventRepository extends CrudRepository<Event, Integer>{

}
