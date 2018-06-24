package myapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import myapp.models.BookReviews;





public interface BookReviewRepository extends CrudRepository<BookReviews, Integer>{
	
	@Query("SELECT br FROM BookReviews br WHERE br.reviewerId = :reviewerId AND br.isbn = :isbn")
	BookReviews findByReviewerIdandIsbn(@Param("reviewerId")String reviewerId,@Param("isbn")String isbn);
	
	@Query("SELECT br FROM BookReviews br WHERE br.isbn = :isbn")
	List<BookReviews> findReviewsOfABook(@Param("isbn")String isbn);
	
	@Query("SELECT br FROM BookReviews br WHERE br.reviewerId = :reviewerId")
	List<BookReviews> findReviewsOfReviewer(@Param("reviewerId")String reviewerId);
}
