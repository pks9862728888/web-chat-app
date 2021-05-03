//package com.demo.webchatapp.controller;
//
//import com.demo.webchatapp.constants.ProjectConstants;
//import com.demo.webchatapp.models.User;
//import com.demo.webchatapp.service.LoginService;
//import com.demo.webchatapp.service.SessionService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class AuthController {
//
//    SessionService sessionService = new SessionService();
//    LoginService loginService = new LoginService();
//
//    @GetMapping("/sign-in")
//    private String getSignInPage(HttpSession session) {
//        return "sign_in";
//    }
//
//    @GetMapping("/sign-up")
//    private String getSignUpPage() {
//        return "sign_up";
//    }
//
//    @GetMapping("/sign-out")
//    private String signOut(Model model, HttpSession session) {
//        sessionService.clearLoginDataFromSession(session);
//        return "redirect:/sign-in";
//    }
//
//    @RequestMapping("/process-signup-request")
//    public String signup(
//            @RequestParam("username") String username,
//            @RequestParam("email") String email,
//            @RequestParam("password") String password,
//            Model model,
//            HttpSession session
//    ) {
//        // Trying to sign up
//        User user = new User(
//                username,
//                email,
//                loginService.encodeToBase64String(password),
//                loginService.generateUID(ProjectConstants.UID_MAX_LENGTH
//                ));
//        UserDAO.createUser(user);
//        model.addAttribute("success", "Sign up success.");
//
//        // Redirecting to sign in page
//        return "redirect:/sign-in";
//    }
//
//    @RequestMapping("/process-login-request")
//    public String login(
//            @RequestParam("username") String username,
//            @RequestParam("password") String password,
//            Model model,
//            HttpSession session
//    ) {
//        try {
//            // Validates credential (puts a check against empty form submission)
//            boolean dataValidationSuccess = loginService.validateLoginCredential(username, password);
//
//            if (dataValidationSuccess == false) {
//                model.addAttribute("error", "Invalid login credentials.");
//                return "redirect:/sign-in";
//            }
//
//            // Trying to login
//            User user = UserDAO.getUserByUsernameAndPassword(username, loginService.encodeToBase64String(password));
//
//            // Saving login data in session
//            sessionService.saveLoginDataInSession(session, user);
//            SessionService.addSessionAttributesToModel(session, model);
//
//            // Return message view
//            return "redirect:/chat";
//        } catch (LoginFailedException e) {
//            model.addAttribute("error", e.getMessage());
//            return "redirect:/sign-in";
//        }
//    }
//
//}
