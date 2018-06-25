package myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import myapp.models.Event;

import myapp.repositories.EventRepository;

@RestController
@CrossOrigin("*")
public class EventServices {
	
	@Autowired
	EventRepository eventRepository;
	
	@PostMapping("/api/event")
	public Event createEvent(@RequestBody Event event){
		System.out.println("inside the create event method");
		return eventRepository.save(event);
	}
	
	@DeleteMapping("/api/event/{eventId}")
	public void deleteEvent(@PathVariable("eventId") int id) {
		eventRepository.deleteById(id);
	}

}
