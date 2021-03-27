package com.demo.upload.controllers;

import com.demo.constants.StatusConstantsInterface;
import com.demo.entities.UploadedImage;
import com.demo.exceptions.InvalidFileFormatException;
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

import java.io.IOException;
import java.util.List;

@Controller
public class UploadedImageController {

    @Value("${image.storage.location}")
    private String storageFolder;

    @Value("${image.storage.location.url}")
    private String storageFolderUrl;

    @Autowired
    private UploadedImageRepository uploadedImageRepository;

    @Autowired
    private SaveFileService saveFileService;

    @Autowired
    private FileValidatorService fileValidatorService;

    @GetMapping("/upload-image")
    public String getUploadImagePage() {
        return "upload-image";
    }

    @PostMapping("/process-uploaded-image")
    public String processUploadedImage(@RequestParam("file") MultipartFile file, Model model) {

        // Checking whether correct file is uploaded or not
        try {
            fileValidatorService.validateImageFile(file.getOriginalFilename());
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            return "status/error";
        }

        // Trying to save file
        try {
            String savedFileName = saveFileService.saveFile(file, storageFolder);

            UploadedImage uploadedImage = new UploadedImage(savedFileName);
            uploadedImageRepository.saveAndFlush(uploadedImage);

            model.addAttribute("message", "File uploaded successfully.");
            model.addAttribute("status", StatusConstantsInterface.SUCCESS);
            return "status/success";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            model.addAttribute("status", StatusConstantsInterface.FAILED);
            return "status/error";
        }
    }

    @GetMapping("/view-all-images")
    public String viewAllImages(Model model) {
        List<UploadedImage> list = uploadedImageRepository.findAll();
        model.addAttribute("images", list);
        model.addAttribute("image_path_prefix", storageFolderUrl);
        return "display-all-images";
    }
}
