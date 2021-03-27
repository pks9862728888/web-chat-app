package com.demo.services.implementations;

import com.demo.exceptions.InvalidFileFormatException;
import com.demo.services.FileValidatorService;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class FileValidatorServiceImpl implements FileValidatorService {

    @Override
    public boolean validateImageFile(String filename) throws InvalidFileFormatException {

        if (filename == null) {
            System.out.println("File name is null");
            throw new InvalidFileFormatException("Filename can not be null");
        } else {
            if (filename.toLowerCase().endsWith("png") || filename.toLowerCase().endsWith(".jpeg")) {
                return true;
            } else {
                System.out.println("Invalid file type: " + filename);
                throw new InvalidFileFormatException("Invalid file format. Allowed formats are image/png, image/jpeg");
            }
        }
    }
}
