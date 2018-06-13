package myapp.repositories;
import org.springframework.data.repository.CrudRepository;
import myapp.models.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
