package com.demo.webchatapp.service;

import javax.servlet.http.HttpSession;

import com.demo.webchatapp.exception.LoginStatusCheckException;
import com.demo.webchatapp.models.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class SessionService {
	
	public void saveLoginDataInSession(HttpSession session, User user) {
		session.setAttribute("id", user.getId());
		session.setAttribute("username", user.getUsername());
		session.setAttribute("email", user.getEmail());
		session.setAttribute("uid", user.getUsername());
	}
	
	public void clearLoginDataFromSession(HttpSession session) {
		session.removeAttribute("id");
		session.removeAttribute("username");
		session.removeAttribute("email");
		session.removeAttribute("uid");
		session.invalidate();
	}
	
	public boolean verifyLoginStatus(HttpSession session) throws LoginStatusCheckException {
		// Checking whether user is logged in
		if (session.getAttribute("uid") != null) {
			return true;
		} else {
			throw new LoginStatusCheckException("You need to login first.");
		}
	}
	
	public static void addSessionAttributesToModel(HttpSession session, Model model) {
		model.addAttribute("id", (int) session.getAttribute("id"));
		model.addAttribute("username", session.getAttribute("username"));
	}

}
