package com.demo.webchatapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.ui.Model;

import com.demo.webchatapp.constants.FileTypeConstants;

public class FileUtils {
	
	private static final String SCRAMBLING_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public static String generateUniqueFileName(String filename) {
		
		final String dateFormat = "-yyyy-MM-dd-sss-";
		
		// Generating unique file name
		StringBuilder newFileName = new StringBuilder(filename.substring(0, filename.lastIndexOf(".")));
		newFileName
			.append(new SimpleDateFormat(dateFormat).format(new Date()))
			.append(generateRandomString(5))
			.append(filename.substring(filename.lastIndexOf(".")));
		
		return newFileName.toString();
	}
	
	public static String generateRandomString(int size) {
		Random random = new Random();
		StringBuilder randomString = new StringBuilder();
		
		// Generating random string of specified size
		while (size-- > 0) {
			randomString.append(SCRAMBLING_STRING.charAt(random.nextInt(SCRAMBLING_STRING.length())));
		}
		
		return randomString.toString();
	}
	
	public static String predictFileType(String filename) {
		
		// Getting the file name extension
		String extension = filename.substring(filename.lastIndexOf(".")).trim();
		String fileType = "";

		// Predicting file type
		if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpeg")) {
			fileType = FileTypeConstants.CONTENT_TYPE_IMAGE;
		} else if (
				extension.equalsIgnoreCase(".xlsx") || 
				extension.equalsIgnoreCase(".xls") ||
				extension.equalsIgnoreCase(".doc") ||
				extension.equalsIgnoreCase(".docx") ||
				extension.equalsIgnoreCase(".ppt") ||
				extension.equalsIgnoreCase(".pptx") ||
				extension.equalsIgnoreCase(".txt") ||
				extension.equalsIgnoreCase(".pdf")
		) {
			fileType  = FileTypeConstants.CONTENT_TYPE_FILE;
		}
		
		return fileType;
	}
	
	public static void addFileTypesToModelObject(Model model) {
		model.addAttribute("CONTENT_TYPE_IMAGE", FileTypeConstants.CONTENT_TYPE_IMAGE);
		model.addAttribute("CONTENT_TYPE_FILE", FileTypeConstants.CONTENT_TYPE_FILE);
		model.addAttribute("CONTENT_TYPE_MESSAGE", FileTypeConstants.CONTENT_TYPE_MESSAGE);
		model.addAttribute("CONTENT_TYPE_IMAGE_AND_MESSAGE", FileTypeConstants.CONTENT_TYPE_IMAGE_AND_MESSAGE);
		model.addAttribute("CONTENT_TYPE_FILE_AND_MESSAGE", FileTypeConstants.CONTENT_TYPE_FILE_AND_MESSAGE);
	}

}
