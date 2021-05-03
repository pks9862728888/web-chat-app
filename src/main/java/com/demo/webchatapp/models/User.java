package com.demo.webchatapp.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity(name = "user")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="username", nullable=false, unique=true)
    private String username;

    @Column(name="email", nullable=false, unique=true)
    private String email;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name="uid", nullable=false)
    private String uid;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_id")
    private List<Connection> fromConnections = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_id")
    private List<Connection> toConnections = new ArrayList<>();

    public User() {
    }

    public User(String username, String email, String password, String uid) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.uid = uid;
    }

    public User(int id, String username, String email, String password, String uid) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Connection> getFromConnections() {
        return fromConnections;
    }

    public void setFrom_connections(List<Connection> fromConnections) {
        this.fromConnections = fromConnections;
    }

    public List<Connection> getToConnections() {
        return toConnections;
    }

    public void setTo_connections(List<Connection> toConnections) {
        this.toConnections = toConnections;
    }

    public List<Connection> getAllConnections() {
        List<Connection> connections = getFromConnections();
        connections.addAll(getToConnections());
        Collections.sort(connections, (o1, o2) -> {
            if (o1.getId() > o2.getId()) {
                return -1;
            } else {
                return 1;
            }
        });
        return connections;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", uid='" + uid + '\'' +
                ", connections =" + getAllConnections() +
                '}';
    }
}