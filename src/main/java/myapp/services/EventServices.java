package myapp.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
	
	
	
	/*@PutMapping("/api/event/{eventId}")
	public Event updateEvent(@PathVariable("eventId") int eventId, @RequestBody Event newEvent) {
		System.out.println(eventId);
		System.out.println("Event Updated");
		Optional<Event> data = eventRepository.findById(eventId);
		if(data.isPresent()) {
			Event event = data.get();
			event.setPublisherId(newEvent.getPublisherId());
			event.setPublisherName(newEvent.getPublisherName());
			event.setPublisher_imgURL(newEvent.getPublisher_imgURL());
			event.setEvent_info(newEvent.getEvent_info());
			eventRepository.save(event);
			return event;
		}
		return null;
	}*/
	
	@PostMapping(value=("/api/user/{userId}/event"), headers="content-type=multipart/*")
	public void updateEvent(@RequestParam("file0") MultipartFile multiPartFileObj, @PathVariable("userId") int userId){
		System.out.println("inside the update event method");
		System.out.println(userId);
		
		AWService aws = new AWService();
		
		File convFile = new File(multiPartFileObj.getOriginalFilename());
		    try {
				convFile.createNewFile();
				FileOutputStream fos = new FileOutputStream(convFile); 
			    fos.write(multiPartFileObj.getBytes());
			    fos.close(); 
			   
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("some error!");
				e.printStackTrace();
			} 
		    
		    try {
		    String fileName = aws.createFile("events",convFile,userId);
		    System.out.println(fileName);
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
		    
		   
		}
	
	/*
		public Event updateEvent(int userId, String fileName) {
				System.out.println(eventId+""+fileName);
				Optional<Event> data = eventRepository.findById(eventId);
				Event newEvent = null;
				if(data.isPresent()) {
					Event event = data.get();
					event.setEvent_imgURL(fileName);
					eventRepository.save(event);
					return event;
				}
				return null;
		}*/
		

		@GetMapping("/api/event")
		public Iterable<Event>findAllEvents(){
			return eventRepository.findAll();
		}
		
		@GetMapping("/api/event/{publisherId}")
		public List<Event>findAllEventsForPublisher(@PathVariable("publisherId") String publisherId){
			List<Event> eventList =  eventRepository.findAllEventsForPublisher(publisherId);
			return eventList; 
			
		}	

		


}
