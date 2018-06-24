package myapp.services;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/api/user")
	public Iterable<User> findAllUser() {
		return userRepository.findAll();
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user) {
		System.out.println(user);
		System.out.println("Inside login");
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
	
		List<User> usersList = userRepository.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
		if(usersList.size() == 0) {
			user =  new User();
			System.out.println(user.getId());
		}
		
		else {
			user = usersList.get(0);
			System.out.println("Here I am :"+user.getId());
		}
		return user;
	}

}
