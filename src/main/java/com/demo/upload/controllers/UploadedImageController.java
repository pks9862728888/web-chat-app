package com.demo.upload.controllers;

import com.demo.constants.StatusConstantsInterface;
import com.demo.entities.UploadedImage;
import com.demo.entities.UploadedImageAsFile;
import com.demo.exceptions.InvalidFileFormatException;
import com.demo.repositories.UploadedImageAsFileRepository;
import com.demo.repositories.UploadedImageRepository;
import com.demo.services.FileValidatorService;
import com.demo.services.SaveFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UploadedImageController {

    @Value("${image.storage.location}")
    private String imageStorageFolder;

    @Autowired
    private UploadedImageRepository uploadedImageRepository;

    @Autowired
    private UploadedImageAsFileRepository uploadedImageAsFileRepository;

    @Autowired
    private SaveFileService saveFileService;

    @Autowired
    private FileValidatorService fileValidatorService;

    @GetMapping("/upload-image-to-shared-location")
    public String getUploadImageToSharedLocationPage() {
        return "upload-image-to-shared-location";
    }

    @PostMapping("/save-uploaded-image-in-shared-location")
    public String saveUploadedImageInSharedLocation(@RequestParam("file") MultipartFile file, Model model) {
        model.addAttribute("STATUS_SUCCESS", StatusConstantsInterface.SUCCESS);

        // Checking whether file with correct format is uploaded or not
        try {
            fileValidatorService.validateImageFile(file.getOriginalFilename());
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            model.addAttribute("status", StatusConstantsInterface.FAILED);
            return "display-upload-status";
        }

        // Trying to save file in disk and database and sending appropriate response
        try {
            String savedFileName = saveFileService.saveFile(
                    file,
                    (new File(imageStorageFolder)).getAbsolutePath()
            );

            UploadedImage uploadedImage = new UploadedImage(savedFileName);
            uploadedImageRepository.saveAndFlush(uploadedImage);

            model.addAttribute("message", "File uploaded successfully.");
            model.addAttribute("status", StatusConstantsInterface.SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            model.addAttribute("status", StatusConstantsInterface.FAILED);
        }
        return "display-upload-status";
    }

    @GetMapping("/view-all-images-from-shared-location")
    public String viewAllImagesFromSharedLocation(Model model) {
        List<UploadedImage> list = uploadedImageRepository.findAll();

        // Creating absolute file path
        for (int i = 0; i < list.size(); i++) {
            list.set(i,
                    new UploadedImage(list.get(i).getId(),
                    ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path(imageStorageFolder)
                            .path("/" + list.get(i).getFileName())
                            .toUriString()
                    )
            );
        }
        model.addAttribute("images", list);
        return "display-all-images";
    }

    @GetMapping("/upload-image-to-database")
    public String getUploadImageToDatabasePage() {
        return "upload-image-to-database";
    }

    @PostMapping("/save-uploaded-image-in-database")
    public String saveUploadedImageInDatabase(@RequestParam("file") MultipartFile file, Model model) {
        model.addAttribute("STATUS_SUCCESS", StatusConstantsInterface.SUCCESS);

        // Checking whether file with correct format is uploaded or not
        try {
            fileValidatorService.validateImageFile(file.getOriginalFilename());
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            model.addAttribute("status", StatusConstantsInterface.FAILED);
            return "display-upload-status";
        }

        // creating an unique file name
        String originalFilename = file.getOriginalFilename();
        String filename = originalFilename.substring(0, originalFilename.lastIndexOf(".")) +
                "-" +
                new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) +
                originalFilename.substring(originalFilename.lastIndexOf("."));

        // Trying to save file in database and sending appropriate response
        try {
            UploadedImageAsFile uploadedImageAsFile = new UploadedImageAsFile(file.getBytes(), filename);
            uploadedImageAsFileRepository.saveAndFlush(uploadedImageAsFile);

            model.addAttribute("message", "File uploaded successfully.");
            model.addAttribute("status", StatusConstantsInterface.SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            model.addAttribute("status", StatusConstantsInterface.FAILED);
        }
        return "display-upload-status";
    }

    @GetMapping("/view-all-images-from-database")
    public String viewAllImagesFromDatabase(Model model) {
        List<UploadedImageAsFile> list = uploadedImageAsFileRepository.findAll();
        model.addAttribute("images", list);
        return "display-all-images-from-blob-file";
    }

}
