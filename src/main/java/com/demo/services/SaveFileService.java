package com.demo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class SaveFileService {

    @Value("${image.storage.location}")
    private String storageFolder;

    public String saveFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String savingFilePath = storageFolder + filename;
        System.out.println("Saving file in: " + savingFilePath);

        File imageFile = new File(savingFilePath);
        if (imageFile.exists()) {
            System.out.println("File with same name already exists: " + savingFilePath);
            throw new IOException("File with same name already exists.");
        }

        Files.copy(file.getInputStream(), Paths.get(savingFilePath), StandardCopyOption.REPLACE_EXISTING);

        return filename;
    }

}
