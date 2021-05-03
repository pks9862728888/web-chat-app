package com.demo.webchatapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class DataService {
	
	public void saveData(MultipartFile file, String message, int senderId, int connectionId) throws IOException {
		
//		Data data = new Data();
//		data.setConnectionId(connectionId);
//		data.setSenderId(senderId);
//		String contentType = "";
//
//		// Setting file data
//		if (!file.isEmpty()) {
//			data.setFilename(FileUtils.generateUniqueFileName(file.getOriginalFilename()));
//			data.setFile(file.getBytes());
//			contentType = FileUtils.predictFileType(file.getOriginalFilename());
//		}
//
//		// Setting message data
//		if (message != null && message.trim().length() != 0) {
//			data.setMessage(message.trim());
//		} else {
//			data.setMessage("");
//		}
//
//		// Setting content type
//		if (contentType.equals(FileTypeConstants.CONTENT_TYPE_IMAGE) && (message == null || message.trim().length() == 0)) {
//			data.setContent_type(FileTypeConstants.CONTENT_TYPE_IMAGE);
//		} else if (contentType.equals(FileTypeConstants.CONTENT_TYPE_FILE) && (message == null || message.trim().length() == 0)) {
//			data.setContent_type(FileTypeConstants.CONTENT_TYPE_FILE);
//		} else if (contentType.equals(FileTypeConstants.CONTENT_TYPE_IMAGE) && (message != null || message.trim().length() != 0)) {
//			data.setContent_type(FileTypeConstants.CONTENT_TYPE_IMAGE_AND_MESSAGE);
//		} else if (contentType.equals(FileTypeConstants.CONTENT_TYPE_FILE) && (message != null || message.trim().length() != 0)) {
//			data.setContent_type(FileTypeConstants.CONTENT_TYPE_FILE_AND_MESSAGE);
//		} else {
//			data.setContent_type(FileTypeConstants.CONTENT_TYPE_MESSAGE);
//		}
//
//		// Setting sent date
//		data.setSentOn(new SimpleDateFormat("dd MMM yyyy hh:mm a").format(new Date()));
//
//		// Saving data in database
//		DataDAO.saveData(data);
	}

}
