package tech.saltyfish.ptcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.saltyfish.ptcart.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);


}
