package pl.strefakursow.springadvanced.repository;

import org.springframework.data.repository.CrudRepository;
import pl.strefakursow.springadvanced.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
