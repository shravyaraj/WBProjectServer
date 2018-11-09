package myapp.services;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Collection;

import java.util.HashSet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	
	@GetMapping("/api/users/{username}")
	public Map <String,String> findUserByUsername(@PathVariable("username") String username) {
		Map<String,String> result = new HashMap <String,String>();
		String exists;
		
		List<User> userlist = (List<User>) userRepository.findUserByUsername(username);
		if(userlist.size() == 0) {
			exists = "false";
		}
		
		else {
			exists = "true";
		}
		
		System.out.println(exists);
		
		result.put("exists"," "+exists);
		return result;
	
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
		System.out.println(user.getUsername());
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
			    
			    String fileName = aws.createFile("cover-pictures",convFile,userId);
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
	
	 @GetMapping("/api/user/{followerId}/{followingId}")
		public void follow(@PathVariable("followerId") int followerId,@PathVariable("followingId") int followingId){
		 	
		 System.out.println(followerId + "is following "+ followingId);
		 Optional<User> follower = userRepository.findById(followerId);
		 Optional<User> following = userRepository.findById(followingId);
		 User followeruser = follower.get();
		 User followinguser = following.get();
		 Collection<User> followersfollowingcollection = follower.get().getFollowingCollection();
		 Collection<User> followedUsersfollwercollection = following.get().getFollowerCollection();
		 followersfollowingcollection.add(followinguser);
		 followedUsersfollwercollection.add(followeruser);
		 
		 followeruser.setFollowingCollection(followersfollowingcollection);
		 userRepository.save(followeruser);
		 
		 
		 followinguser.setFollowerCollection(followedUsersfollwercollection);
		 userRepository.save(followinguser);
		 
 
		 
	 }
	 
	 @GetMapping("/api/unfollow/{followerId}/{followingId}")
		public void unfollow(@PathVariable("followerId") int followerId,@PathVariable("followingId") int followingId){
		 	
		 System.out.println(followerId + "is unfollowing "+ followingId);
		 Optional<User> follower = userRepository.findById(followerId);
		 Optional<User> following = userRepository.findById(followingId);
		 User followeruser = follower.get();
		 User followinguser = following.get();
		 Collection<User> followersfollowingcollection = follower.get().getFollowingCollection();
		 Collection<User> followedUsersfollwercollection = following.get().getFollowerCollection();
		 followersfollowingcollection.remove(followinguser);
		 followedUsersfollwercollection.remove(followeruser);
		 
		 followeruser.setFollowingCollection(followersfollowingcollection);
		 userRepository.save(followeruser);
		 
		 
		 followinguser.setFollowerCollection(followedUsersfollwercollection);
		 userRepository.save(followinguser);
		 

		 
	 }
	 
	 @GetMapping("/api/findfollowers/{userId}")
		public Collection<User> follow(@PathVariable("userId") int userId){
		 	
		 Optional<User> user = userRepository.findById(userId);
		 return user.get().getFollowerCollection();
		 

		 
	 }
	 
	 @GetMapping("/api/findfollowing/{userId}")
		public Collection<User> following(@PathVariable("userId") int userId){
		 	
		 Optional<User> user = userRepository.findById(userId);
		 return user.get().getFollowingCollection();
		 

		 
	 }
	 
	 
	 
	
	
}
	
	
	



