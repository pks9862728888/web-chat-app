package com.demo.services.implementations;

import com.demo.services.SaveFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class SaveFileServiceImpl implements SaveFileService {

    public String saveFile(MultipartFile file, String storageFolderLocation) throws IOException {
        String filename = file.getOriginalFilename();
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

        String savingFilePath = storageFolderLocation + appender + filename;
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
