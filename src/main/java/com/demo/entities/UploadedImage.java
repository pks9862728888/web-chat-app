package com.demo.entities;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table(name = "uploaded_image")
public class UploadedImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="file_name", nullable = false, unique = true)
    private String fileName;

    public UploadedImage() {
    }

    public UploadedImage(int id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public UploadedImage(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "UploadedImage{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                '}';
    }

}
