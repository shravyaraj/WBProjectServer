package myapp.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import myapp.models.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	@Query("Select p from User p where p.username =:username and p.password =:password")
	public List<User>findUserByUsernameAndPassword(@Param("username") String email,@Param("password") String password);

}
