package myapp.repositories;

import org.springframework.data.repository.CrudRepository;

import myapp.models.Topic;


public interface TopicRepository extends CrudRepository<Topic, Integer> {

}
