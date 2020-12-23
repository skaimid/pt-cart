package tech.saltyfish.ptcart.service;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.saltyfish.ptcart.model.entity.Link;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {

    List<Link> findByOwnedChannel_ChannelId(Long ownedChannelId);

    List<Link> findByOwnedChannel_ChannelName(String ownedChannelName);
}
