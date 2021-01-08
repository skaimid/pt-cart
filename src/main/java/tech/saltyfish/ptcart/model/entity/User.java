package tech.saltyfish.ptcart.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    private @Id
    @GeneratedValue
    Long userId;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;

    @Column(unique = true)
    private String token;


    @OneToMany(mappedBy = "ownedUser")
    private List<ChannelEntity> channelEntitySet;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    @JsonIgnore
    public List<ChannelEntity> getChannelSet() {
        return channelEntitySet;
    }

    public void setChannelSet(List<ChannelEntity> channelEntitySet) {
        this.channelEntitySet = channelEntitySet;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @JsonIgnore
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return username.equals(user.username) &&
                password.equals(user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(channelEntitySet, user.channelEntitySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role, channelEntitySet);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", channelSet=" + channelEntitySet +
                '}';
    }
}
