package tech.saltyfish.ptcart.service;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.saltyfish.ptcart.model.entity.ChannelEntity;

import java.util.List;

public interface ChannelEntityRepository extends JpaRepository<ChannelEntity, Long> {

    List<ChannelEntity> findByOwnedUser_Username(String ownedUsername);

    ChannelEntity findByChannelIdAndOwnedUser_Username(Long channelId, String ownedUser_username);

//    List<ChannelEntity> findByOwnedUser_UserId(Long ownedUserId);
}
