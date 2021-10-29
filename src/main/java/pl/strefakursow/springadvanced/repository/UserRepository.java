package pl.strefakursow.springadvanced.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import pl.strefakursow.springadvanced.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByConfirmationToken(String token);

}
