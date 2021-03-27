package com.demo.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SaveFileService {

    public String saveFile(MultipartFile file, String storageFolderLocation) throws IOException;

}
