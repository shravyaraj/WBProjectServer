package myapp.services;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import myapp.models.EventCard;
import myapp.repositories.EventCardRepository;


@RestController
@CrossOrigin("*")
public class EventCardService {
	
	@Autowired
	EventCardRepository eventCardRepository;
	HttpSession session1;
	
	@GetMapping("/api/eventcard/{eventcardId}")
	public Optional<EventCard> findCourseById(@PathVariable("eventcardId") int eventcardId) {
		return eventCardRepository.findById(eventcardId);
	}
	
	@GetMapping("/api/eventcard")
	public Iterable<EventCard> findAllEventCards() {
		return eventCardRepository.findAll();
	}
	
	@PostMapping("/api/eventcard")
	public EventCard createEventCard(@RequestBody EventCard event) {
		return eventCardRepository.save(event);
	}
	
	@DeleteMapping("/api/eventcard/{eventcardId}")
	public void delete(@PathVariable("eventcardId") int eventcardId) {
		eventCardRepository.deleteById(eventcardId);	
	}
}
