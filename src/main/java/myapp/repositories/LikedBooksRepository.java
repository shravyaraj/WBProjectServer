package myapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import myapp.models.LikedBooks;




public interface LikedBooksRepository extends CrudRepository<LikedBooks, Integer>{
	
	@Query("SELECT lb FROM LikedBooks lb WHERE lb.userId = :userId")
	List<LikedBooks> findLikedBooksForUser(@Param("userId")String userId);
}
