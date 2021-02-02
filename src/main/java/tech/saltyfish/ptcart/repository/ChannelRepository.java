package tech.saltyfish.ptcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.saltyfish.ptcart.model.entity.ChannelEntity;

import java.util.List;

public interface ChannelRepository extends JpaRepository<ChannelEntity, Long> {

    List<ChannelEntity> findByOwnedUser_Username(String ownedUsername);

    List<ChannelEntity> findByOwnedUser_UserId(Long ownedUserId);
}
