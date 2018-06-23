package myapp.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



import myapp.models.Topic;
import myapp.repositories.TopicRepository;

@RestController
@CrossOrigin("*")
public class TopicService {
@Autowired
TopicRepository topicRepository;



@PostMapping("/api/topic")
public Topic createTopic(@RequestBody Topic topic1) {
	return topicRepository.save(topic1);
}

@GetMapping("/api/topic")
public Iterable<Topic>findAllTopics(){
	return topicRepository.findAll();
	
}

@DeleteMapping("/api/topic/{topicId}")
public void delete(@PathVariable("topicId") int id) {
	topicRepository.deleteById(id);	
}


}
