package myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import myapp.models.Blog;
import myapp.models.BookReviews;
import myapp.repositories.BlogRepository;
import myapp.repositories.BookReviewRepository;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class BlogService {
	
	@Autowired
	BlogRepository blogRepository;
	
	@PostMapping("/api/blog/{bloggerId}")
	public Blog createBlog(@PathVariable("bloggerId") String bloggerId, @RequestBody Blog newBlog) {
		
		System.out.println("inside create Blog ");
		System.out.println(newBlog.getBlog());
		
	return blogRepository.save(newBlog);			
	}

}
