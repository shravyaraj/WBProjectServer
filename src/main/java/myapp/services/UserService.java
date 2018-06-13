package myapp.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import myapp.models.User;
import myapp.repositories.UserRepository;

@RestController
@CrossOrigin("*")
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user){
		System.out.println("inside the create user method");
		return userRepository.save(user);
	}
	
	

}
