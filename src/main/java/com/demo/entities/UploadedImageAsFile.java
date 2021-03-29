package com.demo.entities;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Base64;

@Entity(name = "uploaded_image_as_file")
public class UploadedImageAsFile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Lob
    @Column(name = "file")
    private byte[] file;

    @Column(name = "file_name")
    private String fileName;

    public UploadedImageAsFile() {
    }

    public UploadedImageAsFile(byte[] file, String fileName) {
        this.file = file;
        this.fileName = fileName;
    }

    public UploadedImageAsFile(int id, byte[] file, String fileName) {
        this.id = id;
        this.file = file;
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public String getBase64EncodedFile() {
        return Base64.getEncoder().encodeToString(getFile());
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "UploadedFileAsImage{" +
                "id=" + id +
                ", file=" + Arrays.toString(file) +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
