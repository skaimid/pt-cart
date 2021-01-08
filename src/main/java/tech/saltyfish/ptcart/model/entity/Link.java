package tech.saltyfish.ptcart.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;


@Entity
public class Link {
    private @Id
    @GeneratedValue
    Long linkId;

    private String url;


    @ManyToOne
    private ChannelEntity ownedChannelEntity;

    public Link(String url, ChannelEntity ownedChannelEntity) {
        this.url = url;
        this.ownedChannelEntity = ownedChannelEntity;
    }

    public Link(String url) {
        this.url = url;
    }

    public Link() {
    }

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonIgnore
    public ChannelEntity getOwnedChannel() {
        return ownedChannelEntity;
    }


    public void setOwnedChannel(ChannelEntity ownedChannelEntity) {
        this.ownedChannelEntity = ownedChannelEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;
        Link link = (Link) o;
        return linkId.equals(link.linkId) &&
                url.equals(link.url) &&
                ownedChannelEntity.equals(link.ownedChannelEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkId, url, ownedChannelEntity);
    }

    @Override
    public String toString() {
        return "Link{" +
                "linkId=" + linkId +
                ", url='" + url + '\'' +
                ", ownedChannel=" + ownedChannelEntity +
                '}';
    }
}
