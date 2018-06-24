package myapp.services;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/api/user/{userId}")
	public Optional<User> findUserById(@PathVariable("userId") String userId){
		System.out.println("inside the findUserById method");
		return userRepository.findById(Integer.parseInt(userId));
		
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
	
	@PostMapping(value="/api/searchuser")
	public Set<User> serachUser(@RequestBody String search)
	{
		
		List<User> listByUserName=new ArrayList<>();
		List<User> listByFirstName=new ArrayList<>();
		List<User> listByLastName=new ArrayList<>();
		
		Set<User> finalSet= new HashSet<User>();
		search = search.substring(1,search.length()-2);
		System.out.println("******************inside search method************************"+search);
			
		listByUserName = userRepository.findAllByUser(search);
		listByFirstName= userRepository.findByFirstName(search);
		listByLastName= userRepository.findByLastName(search);
		
		
		if(!listByUserName.isEmpty())
		finalSet.addAll(listByUserName);
		if(!listByFirstName.isEmpty())
		finalSet.addAll(listByFirstName);
		if(!listByLastName.isEmpty())
		finalSet.addAll(listByLastName);
		
		
		System.out.println(finalSet);
		return finalSet;
		
	}
	
	
	

}
