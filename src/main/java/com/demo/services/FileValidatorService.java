package com.demo.services;

import com.demo.exceptions.InvalidFileFormatException;

public interface FileValidatorService {

    boolean validateImageFile(String filename) throws InvalidFileFormatException;

}
