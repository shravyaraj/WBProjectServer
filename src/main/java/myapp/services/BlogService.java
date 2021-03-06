package myapp.services;

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
import org.springframework.web.bind.annotation.RestController;
import myapp.models.Blog;
import myapp.repositories.BlogRepository;


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
	
	@GetMapping("/api/blog")
	public Iterable<Blog>findAllBlogs(){
		return blogRepository.findAll();
		
	}
	
	@PutMapping("/api/blog/{blogId}")
	public Blog updateBlog(@RequestBody Blog blog,@PathVariable("blogId") int blogId){
		Blog blog1 = blogRepository.findById(blogId).get();
		blog1.setBlog(blog.getBlog());
		blogRepository.save(blog1);
		return blog1;
		}
	
	@GetMapping("/api/blogger/{bloggerID}")
	public List<Blog>findAllBlogsforUser(@PathVariable("bloggerID") String bloggerId){
		List<Blog> blogList =  blogRepository.findAllBlogsforUser(bloggerId);
		return blogList; 
		
	}
	
	
	@DeleteMapping("/api/bloger/{blogId}")
	public void delete(@PathVariable("blogId") int id) {
		System.out.println("hello");
		blogRepository.deleteById(id);	
	}
	

}
