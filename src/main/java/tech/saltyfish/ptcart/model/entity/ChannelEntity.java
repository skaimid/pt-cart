package tech.saltyfish.ptcart.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
public class ChannelEntity {
    private @Id
    @GeneratedValue
    Long channelId;

    private String channelName;


    @ManyToOne()
    private User ownedUser;


    @OneToMany(mappedBy = "ownedChannelEntity", cascade = CascadeType.REMOVE)
    private List<Link> linkSet;

    public ChannelEntity() {
    }

    public ChannelEntity(String channelName) {
        this.channelName = channelName;
    }

    public ChannelEntity(String channelName, User ownedUser) {
        this.channelName = channelName;
        this.ownedUser = ownedUser;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long id) {
        this.channelId = id;
    }


    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @JsonIgnore
    public User getOwnedUser() {
        return ownedUser;
    }

    public void setOwnedUser(User ownedUser) {
        this.ownedUser = ownedUser;
    }


    public List<Link> getLinkSet() {
        return linkSet;
    }

    public void setLinkSet(List<Link> linkSet) {
        this.linkSet = linkSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChannelEntity)) return false;
        ChannelEntity channelEntity = (ChannelEntity) o;
        return channelId.equals(channelEntity.channelId) &&
                channelName.equals(channelEntity.channelName) &&
                ownedUser.equals(channelEntity.ownedUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channelId, channelName, ownedUser);
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + channelId +
                ", channelName='" + channelName + '\'' +
                ", user=" + ownedUser +
                '}';
    }
}
