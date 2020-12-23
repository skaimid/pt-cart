package tech.saltyfish.ptcart.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
public class Channel {
    private @Id
    @GeneratedValue
    Long channelId;

    private String channelName;


    @ManyToOne()
    private User ownedUser;


    @OneToMany(mappedBy = "ownedChannel", cascade = CascadeType.REMOVE)
    private List<Link> linkSet;

    public Channel() {
    }

    public Channel(String channelName) {
        this.channelName = channelName;
    }

    public Channel(String channelName, User ownedUser) {
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
        if (!(o instanceof Channel)) return false;
        Channel channel = (Channel) o;
        return channelId.equals(channel.channelId) &&
                channelName.equals(channel.channelName) &&
                ownedUser.equals(channel.ownedUser);
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
