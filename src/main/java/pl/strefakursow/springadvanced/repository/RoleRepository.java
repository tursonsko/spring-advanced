package pl.strefakursow.springadvanced.repository;

import org.springframework.data.repository.CrudRepository;
import pl.strefakursow.springadvanced.entity.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}


