package ing.com.storemanagementapi.repository;

import ing.com.storemanagementapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsernameAndPassword(String username, String password);
}