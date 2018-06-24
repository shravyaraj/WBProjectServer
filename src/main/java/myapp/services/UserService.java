package myapp.services;
<<<<<<< HEAD
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		System.out.println(userId);
		System.out.println("Updated");
		Optional<User> data = userRepository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setRole(newUser.getRole());
			user.setEmail(newUser.getEmail());
			user.setRole(newUser.getRole());
			user.setDateOfBirth(newUser.getDateOfBirth());
			user.setBio(newUser.getBio());
			user.setCategory(newUser.getCategory());
			userRepository.save(user);
			return user;
		}
		return null;
	}
	
	
	@GetMapping("/api/profile/{username}")
	public User findUserByUsername(@PathVariable("username") String username) {
		Optional<User> data = userRepository.findUserByUsername(username);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	
	    @PostMapping("/api/login")
	    public User login(@RequestBody User user) {
		  System.out.println(user);
		  System.out.println("Inside login");
		  System.out.println(user.getEmail());
		  System.out.println(user.getPassword());
		List<User> usersList = userRepository.findUserByUsernameAndPassword(user.getEmail(),user.getPassword());
		
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
	    
	    @PostMapping(value=("/api/user/{userId}/coverPicture"), headers="content-type=multipart/*")
		public void updateCover(@RequestParam("file0") MultipartFile multiPartFileObj, @PathVariable("userId") int userId){
			System.out.println("inside the update cover picture method");
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
			    
			    String fileName = aws.createFile(convFile,userId);
			    updateCoverPic(userId, fileName);
			}
	    
	        public User updateCoverPic(int userId, String fileName) {
			System.out.println(userId);
			System.out.println("YAAAAAAAY"+fileName);
			Optional<User> data = userRepository.findById(userId);
			User newUser = null;
			if(data.isPresent()) {
				User user = data.get();
				user.setCoverPic(fileName);
				userRepository.save(user);
				return user;
			}
			return null;
		}
	}

}

