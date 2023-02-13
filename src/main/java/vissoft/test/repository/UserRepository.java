package vissoft.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vissoft.test.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
