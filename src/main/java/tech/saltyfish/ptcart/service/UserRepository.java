package tech.saltyfish.ptcart.service;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.saltyfish.ptcart.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);


}
