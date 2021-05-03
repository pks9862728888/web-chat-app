//package com.demo.webchatapp.controller;
//
//import java.io.ByteArrayInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.Collections;
//import java.util.List;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import custom.exception.ConnectionDoesNotExistException;
//import custom.exception.DuplicateRequestException;
//import custom.exception.LoginStatusCheckException;
//import custom.exception.UserNotFoundException;
//import onlinechat.dao.ConnectionDAO;
//import onlinechat.dao.DataDAO;
//import onlinechat.dao.UserDAO;
//import onlinechat.entity.Connection;
//import onlinechat.entity.Data;
//import onlinechat.model.ConnectionDataModel;
//import onlinechat.service.DataService;
//import onlinechat.service.SessionService;
//import onlinechat.util.FileUtils;
//
//@Controller
//public class ChatController {
//
//	SessionService sessionService = new SessionService();
//	DataService dataService = new DataService();
//
//	@GetMapping("/add-user")
//	public String sendGetUserPage(Model model, HttpSession session) {
//		// Verify whether logged in or whether session is not expired
//		try {
//			sessionService.verifyLoginStatus(session);
//		} catch (LoginStatusCheckException e) {
//			model.addAttribute("error", e.getMessage());
//			return "redirect:/sign-in";
//		}
//		return "add_connection";
//	}
//
//	@PostMapping("/add-connection")
//	public String addConnection(
//			@RequestParam("username") final String connectionUserName,
//			Model model,
//			HttpSession session
//	) {
//		// Verify whether logged in or whether session is not expired
//		try {
//			sessionService.verifyLoginStatus(session);
//		} catch (LoginStatusCheckException e) {
//			model.addAttribute("error", e.getMessage());
//			return "redirect:/sign-in";
//		}
//
//		// Verify input and report errors
//		if (connectionUserName == null || connectionUserName.trim().length() == 0) {
//			model.addAttribute("error", "Invalid username.");
//			return "redirect:/add-user";
//		} else if (connectionUserName.trim().equals(session.getAttribute("username"))) {
//			model.addAttribute("error", "Invalid request. Can not add self.");
//			return "redirect:/add-user";
//		}
//
//		// Find user id if user exists with username
//		int otherUsersId;
//		try {
//			otherUsersId = UserDAO.getUserIdByUsername(connectionUserName);
//		} catch (UserNotFoundException e) {
//			model.addAttribute("error", e.getMessage());
//			return "redirect:/add-user";
//		}
//
//		// Returning if connection already exists
//		try {
//			ConnectionDAO.checkWhetherConnectionAlreadyExistsBetweenTwoUsers((int) session.getAttribute("id"), otherUsersId);
//		} catch (DuplicateRequestException e) {
//			model.addAttribute("error", e.getMessage());
//			return "redirect:/add-user";
//		}
//
//		// Adding connection
//		int connectionId = ConnectionDAO.createConnection(new Connection(
//				(int) session.getAttribute("id"),
//				otherUsersId
//		));
//
//		return "redirect:/chat";
//	}
//
//	@GetMapping("/chat")
//	public String getChatPage(Model model, HttpSession session) {
//		// Verify whether logged in or whether session is not expired
//		try {
//			sessionService.verifyLoginStatus(session);
//		} catch (LoginStatusCheckException e) {
//			model.addAttribute("error", e.getMessage());
//			return "redirect:/sign-in";
//		}
//
//		// Fetching chats
//		try {
//			List<ConnectionDataModel> connections = ConnectionDAO.getAllConnectionsIdNameFromId((int) session.getAttribute("id"));
//			model.addAttribute("connections", connections);
//
//			// Getting connection chat if connection exists
//			if (connections.size() > 0) {
//				List<Data> chatData = DataDAO.getAllDataFromConnectionId(connections.get(0).getId());
//
//				// Updating status as seen if sender id is not equal to current user id
//				for (Data d: chatData) {
//					if (d.getSenderId() != (int) session.getAttribute("id")) {
//						DataDAO.setDataAsSeen(d.getConnectionId(), d.getId());
//					}
//				}
//				model.addAttribute("chatData", chatData);
//				model.addAttribute("recipientId", connections.get(0).getRecipientId());
//				model.addAttribute("connectionId", connections.get(0).getId());
//				FileUtils.addFileTypesToModelObject(model);
//			} else {
//				model.addAttribute("chatData", Collections.emptyList());
//			}
//		} catch (UserNotFoundException e) {
//			model.addAttribute("error", e.getMessage());
//			return "redirect:/sign-in";
//		}
//
//		SessionService.addSessionAttributesToModel(session, model);
//		return "chat";
//	}
//
//	@GetMapping("/chat/{id}")
//	public String getChatPage(@PathVariable("id") int connectionId, Model model, HttpSession session) {
//		// Verify whether logged in or whether session is not expired
//		try {
//			sessionService.verifyLoginStatus(session);
//		} catch (LoginStatusCheckException e) {
//			model.addAttribute("error", e.getMessage());
//			return "redirect:/sign-in";
//		}
//
//		// Verify whether connection exists between the given id and the user
//		try {
//			ConnectionDAO.checkWhetherConnectionAlreadyExistsWithConnectionIdAndUserId(connectionId, (int) session.getAttribute("id"));
//		} catch (ConnectionDoesNotExistException e) {
//			model.addAttribute("error", e.getMessage());
//			return "error";
//		}
//
//		// Fetching chats
//		try {
//			List<ConnectionDataModel> connections = ConnectionDAO.getAllConnectionsIdNameFromId((int) session.getAttribute("id"));
//			model.addAttribute("connections", connections);
//
//			// Getting connection chat if connection exists
//			List<Data> chatData = DataDAO.getAllDataFromConnectionId(connectionId);
//
//			// Updating status as seen if sender id is not equal to current user id
//			for (Data d: chatData) {
//				if (d.getSenderId() != (int) session.getAttribute("id")) {
//					DataDAO.setDataAsSeen(d.getConnectionId(), d.getId());
//				}
//			}
//
//			model.addAttribute("chatData", chatData);
//			model.addAttribute("recipientId", connections.get(0).getRecipientId());
//			model.addAttribute("connectionId", connectionId);
//			SessionService.addSessionAttributesToModel(session, model);
//		} catch (UserNotFoundException e) {
//			model.addAttribute("error", e.getMessage());
//			return "redirect:/sign-in";
//		}
//
//		model.addAttribute("session", session);
//		FileUtils.addFileTypesToModelObject(model);
//		return "chat";
//	}
//
//	@PostMapping("/send-message/{connectionId}/{recipientId}")
//	public String sendMessage(
//			@RequestParam("file") MultipartFile file,
//			@RequestParam("message") String message,
//			@PathVariable("recipientId") int recipientId,
//			@PathVariable("connectionId") int connectionId,
//			Model model,
//			HttpSession session
//	) {
//		// Verify whether logged in or whether session is not expired
//		try {
//			sessionService.verifyLoginStatus(session);
//		} catch (LoginStatusCheckException e) {
//			model.addAttribute("error", e.getMessage());
//			return "redirect:/sign-in";
//		}
//
//		// Validate form data
//		if (file.isEmpty() && (message == null || message.trim().length() == 0)) {
//			model.addAttribute("error", "File can not be empty");
//			return "redirect:/chat/{connectionId}";
//		}
//
//		// Verify whether recipient exists
//		try {
//			ConnectionDAO.checkWhetherConnectionAlreadyExistsBetweenTwoUsers(recipientId, (int) session.getAttribute("id"));
//			model.addAttribute("error", "This connection user does not exist.");
//			recipientId = -1;
//		} catch (DuplicateRequestException e) {}
//
//
//		// Saving data
//		try {
//			dataService.saveData(file, message, (int) session.getAttribute("id"), connectionId);
//		} catch (IOException e) {
//			e.printStackTrace();
//			model.addAttribute("error", "Unable to send data");
//		}
//
//		return "redirect:/chat";
//	}
//
//	@GetMapping("/download/{data_id}")
//	public Data downloadDocument(
//			@PathVariable("data_id") int dataId,
//			HttpServletResponse response,
//			Model model
//	) {
//		Data data = new Data();
//
//		try {
//			// Fetching data from database
//			data = DataDAO.getAllDataFromDataId(dataId);
//
//			// Adding file to response body
//			response.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\"", data.getFilename()));
//			InputStream inputStream = new ByteArrayInputStream(data.getFile());
//			OutputStream outputStream = response.getOutputStream();
//			IOUtils.copy(inputStream, outputStream);
//
//			// Flushing and closing the streams;
//			outputStream.flush();
//			IOUtils.closeQuietly(inputStream);
//			IOUtils.closeQuietly(outputStream);
//		} catch (FileNotFoundException e) {} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return data;
//	}
//
//	@GetMapping("/delete/data/{connectionId}/{dataId}")
//	public String deleteData(
//			@PathVariable("connectionId") int connectionId,
//			@PathVariable("dataId") int dataId,
//			Model model,
//			HttpSession session
//	) {
//		// Verify whether logged in or whether session is not expired
//		try {
//			sessionService.verifyLoginStatus(session);
//		} catch (LoginStatusCheckException e) {
//			model.addAttribute("error", e.getMessage());
//			return "redirect:/sign-in";
//		}
//
//		// Delete data
//		DataDAO.deleteDataWithId(connectionId, dataId);
//
//		return "redirect:/chat/" + connectionId;
//	}
//
//	@GetMapping("/delete/connection/{connectionId}")
//	public String deleteConnection(
//			@PathVariable("connectionId") int connectionId,
//			Model model,
//			HttpSession session
//	) {
//		// Verify whether logged in or whether session is not expired
//		try {
//			sessionService.verifyLoginStatus(session);
//		} catch (LoginStatusCheckException e) {
//			model.addAttribute("error", e.getMessage());
//			return "redirect:/sign-in";
//		}
//
//		// Delete connection
//		ConnectionDAO.deleteConnection(connectionId);
//		model.addAttribute("success", "Connection removed successfully");
//
//		return "redirect:/chat";
//	}
//}
