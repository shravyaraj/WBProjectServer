package myapp.repositories;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import myapp.models.User;



public interface UserRepository extends CrudRepository<User, Integer>{

	@Query("Select p from User p where p.username =:username and p.password =:password")
	public List<User>findUserByUsernameAndPassword(@Param("username") String email,@Param("password") String password);
	
	@Query("SELECT usr FROM User usr WHERE LOWER(usr.username) LIKE CONCAT(LOWER(:username),'%')")
	List<User> findAllByUser(@Param("username")String username);
	
	@Query("SELECT usr FROM User usr WHERE LOWER(usr.firstName) LIKE CONCAT(LOWER(:firstName),'%')")
	public List<User> findByFirstName(@Param("firstName") String firstName);
	
	@Query("SELECT usr FROM User usr WHERE LOWER(usr.lastName) LIKE CONCAT(LOWER(:lastName),'%')")
	public List<User> findByLastName(@Param("lastName") String lastName);

	@Query("SELECT u FROM User u WHERE u.username=:username")
	Optional<User> findUserByUsername(
			@Param("username") String username);

}
