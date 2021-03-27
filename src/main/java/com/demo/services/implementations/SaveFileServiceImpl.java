package com.demo.services.implementations;

import com.demo.services.SaveFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SaveFileServiceImpl implements SaveFileService {

    public String saveFile(MultipartFile file, String storageFolderLocation) throws IOException {
        // creating an unique file name
        String originalFilename = file.getOriginalFilename();
        String filename = originalFilename.substring(0, originalFilename.lastIndexOf(".")) +
                "-" +
                new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) +
                originalFilename.substring(originalFilename.lastIndexOf("."));

        // Finding which appender to use to create fully qualified file name
        String appender;

        if (storageFolderLocation.endsWith("/") || storageFolderLocation.endsWith("\\")) {
            appender = "";
        } else {
            if (storageFolderLocation.contains("/")) {
                appender = "/";
            } else {
                appender = "\\";
            }
        }

        // Creating fully qualified file path
        String savingFilePath = storageFolderLocation + appender + filename;
        System.out.println("Saving file in: " + savingFilePath);

        // Throwing exception if file with same name exists
        File imageFile = new File(savingFilePath);
        if (imageFile.exists()) {
            System.out.println("File with same name already exists: " + savingFilePath);
            throw new IOException("File with same name already exists.");
        }

        // Saving file in upload folder
        Files.copy(file.getInputStream(), Paths.get(savingFilePath), StandardCopyOption.REPLACE_EXISTING);

        return filename;
    }

}
