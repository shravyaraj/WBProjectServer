package myapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import myapp.models.LikedBooks;
import myapp.repositories.LikedBooksRepository;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class LikedBooksServices {
	@Autowired
	LikedBooksRepository likedRepository;
	@PostMapping("/api/like")
	public LikedBooks createLikedBooks(@RequestBody LikedBooks likedBook) {
		return likedRepository.save(likedBook);
	}
	
	@GetMapping("/api/findBooks/{userId}")
	public List<LikedBooks> findLikedBooksforAUser(@PathVariable("userId") String userId)
	{
		return likedRepository.findLikedBooksForUser(userId);
	}
	
	@DeleteMapping("/api/like/{likeId}")
	public void deleteLikedBook(@PathVariable("likeId") int likeId)
	{
		System.out.println("inside delete like");
		likedRepository.deleteById(likeId);
	}
		
}
