package pl.strefakursow.springadvanced.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import pl.strefakursow.springadvanced.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Optional<Role> findByName(String name);

}
