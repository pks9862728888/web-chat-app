package com.demo.upload.controllers;

import com.demo.entities.Test;
import com.demo.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/upload-image")
    public String getUploadImagePage() {
        return "upload-image";
    }

    @PostMapping("/process-uploaded-image")
    public String processUploadedImage(final Test test, Model model) {
        String name = test.getName();

        if (name == null || name.trim().length() == 0) {
            model.addAttribute("message", "Image upload failed");
            return "upload-status/error";
        } else {
            testRepository.saveAndFlush(test);
            model.addAttribute("message", "Image uploaded successfully!");
            return "upload-status/success";
        }
    }

    @GetMapping("/view-all-images")
    public String viewAllImages(Model model) {
        List<Test> list = testRepository.findAll();
        model.addAttribute("images", list);
        return "display-all-images";
    }
}
