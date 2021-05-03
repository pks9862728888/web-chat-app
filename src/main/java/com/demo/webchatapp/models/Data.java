package com.demo.webchatapp.models;


import java.util.Base64;

import javax.persistence.*;

@Entity(name = "data")
@Table(name = "data")
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "connection_id")
    private int connectionId;

    @Column(name = "sender_id")
    private int senderId;

    @Column(name = "filename")
    private String filename;

    @Column(name = "content_type")
    private String contentType;

    @Lob
    @Column(name = "file")
    private byte[] file;

    @Column(name = "message", nullable = true)
    private String message;
    
    @Column(name = "seen")
    private int seen;
    
    @Column(name = "sent_on", nullable = false)
    private String sentOn;

    public Data() {
    }

    public Data(int id, int connectionId, int senderId, String filename, String contentType, byte[] file, String message, int seen, String sentOn) {
        this.id = id;
        this.connectionId = connectionId;
        this.senderId = senderId;
        this.filename = filename;
        this.contentType = contentType;
        this.file = file;
        this.message = message;
        this.seen = seen;
        this.sentOn = sentOn;
    }

    public Data(int connectionId, int senderId, String filename, String contentType, byte[] file, String message, int seen, String sentOn) {
        this.connectionId = connectionId;
        this.senderId = senderId;
        this.filename = filename;
        this.contentType = contentType;
        this.file = file;
        this.message = message;
        this.seen = seen;
        this.sentOn = sentOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContent_type(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }
    
    public void setSentOn(String sentOn) {
        this.sentOn = sentOn;
    }

    public String getSentOn() {
        return sentOn;
    }
    
    public String getBase64EncodedImageString() {
		return Base64.getEncoder().encodeToString(getFile());
	}

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", connectionId=" + connectionId +
                ", senderId='" + senderId + '\'' +
                ", filename='" + filename + '\'' +
                ", contentType='" + contentType + '\'' +
                ", file=" + file +
                ", message='" + message + '\'' +
                ", seen='" + seen + '\'' +
                ", sentOn='" + sentOn + '\'' +
                '}';
    }
}