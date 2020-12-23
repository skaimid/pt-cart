package tech.saltyfish.ptcart.service;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.saltyfish.ptcart.model.entity.Channel;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

    List<Channel> findByOwnedUser_Username(String ownedUsername);

    List<Channel> findByOwnedUser_UserId(Long ownedUserId);
}
