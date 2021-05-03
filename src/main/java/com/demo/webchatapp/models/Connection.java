package com.demo.webchatapp.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity(name = "connection")
@Table(name = "connection")
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="from_id", nullable=false)
    private int fromId;

    @Column(name="to_id", nullable=false)
    private int toId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "connection_id")
    private List<Data> connectionData = new ArrayList<>();

    public Connection() {
    }

    public Connection(int fromId, int toId) {
        this.fromId = fromId;
        this.toId = toId;
    }

    public Connection(int id, int fromId, int toId) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public List<Data> getConnectionData() {
        return connectionData;
    }

    public void setConnectionData(List<Data> connectionData) {
        this.connectionData = connectionData;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", connectionData=" + connectionData +
                '}';
    }
}
