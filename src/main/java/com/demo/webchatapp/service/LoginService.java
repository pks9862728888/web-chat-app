package com.demo.webchatapp.service;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	public String generateUID(int uidLength) {
		
		String charSet = "abcdefghijklmnopqrstuvwzyxABCDEFGHOJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		
		StringBuilder uid = new StringBuilder();
		
		// Generating UID
		uid.append(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		while (uid.length() < uidLength) {
			uid.append(charSet.charAt(random.nextInt(charSet.length())));
		}
		
		return uid.toString();
	}
	
	public boolean validateLoginCredential(String username, String password) {
		if (username.length() == 0 || password.length() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public String encodeToBase64String(String password) {
		return Base64.getEncoder().encodeToString(password.getBytes());
	}

}
