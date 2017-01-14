package org.pkb.springlogin.controller;

import org.pkb.springlogin.model.UserInfo;
import org.pkb.springlogin.model.ValidationResponse;
import org.pkb.springlogin.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Base64;

@Controller
public class MainController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private Environment env;

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("sitekey",env.getProperty("data-sitekey"));
        return "welcomePage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseBody
    public ValidationResponse submitForm(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        ValidationResponse validationResponse =
                userRegistrationService.registerUser(userInfo, request.getRemoteAddr());
        if (validationResponse.getStatus() == "success") {
            validationResponse.setRedirectUrl("/login?registerSuccess=success");
        }
        return validationResponse;
    }

    @RequestMapping(value = "/enableUser", method = RequestMethod.GET)
    public String enableUser(@RequestParam("userName") String userName, Model model) {
        try {
            String decodeUserName = new String(Base64.getDecoder().decode(userName), "utf-8");
            boolean userNameExists = userRegistrationService.findUserNameIfExists
                    (decodeUserName);
            if (userNameExists) {
                userRegistrationService.enableUser(decodeUserName);
            }
            model.addAttribute("enableUser", "success");
        } catch (UnsupportedEncodingException e) {
            return "403Page";
        }

        return "loginPage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model,@RequestParam
            (value = "registerSuccess",required = false) String success ) {
        model.addAttribute("userRegisterSuccess",success);
        return "loginPage";

    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
        // After user login successfully.
        String userName = principal.getName();
        model.addAttribute("message", " Welcome" + " " + userName + "!!");
        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("message", "Hi " + principal.getName()
                    + "<br> You do not have permission to access this page!");
        } else {
            model.addAttribute("msg",
                    "You do not have permission to access this page!");
        }
        return "403Page";
    }

    @RequestMapping(value = "/checkIfUserNameExists", method = RequestMethod.GET)
    @ResponseBody
    public ValidationResponse checkIfUserNameExists(@RequestParam("userName") String userName) {
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setSuccessObject( userRegistrationService.findUserNameIfExists(userName));
        return validationResponse;
    }

}