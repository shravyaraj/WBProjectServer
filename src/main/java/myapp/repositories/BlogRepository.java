package myapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import myapp.models.Blog;
import myapp.models.BookReviews;

public interface BlogRepository extends CrudRepository<Blog, Integer> {
	@Query("SELECT br FROM Blog br WHERE br.bloggerId = :bloggerId")
	Blog findByBloggerId(@Param("bloggerId")String bloggerId);
	
	@Query("SELECT br FROM Blogs br WHERE br.bloggerId = :bloggerId")
	List<Blog> findBlogsOfBlogger(@Param("bloggerId")String bloggerId);

}
