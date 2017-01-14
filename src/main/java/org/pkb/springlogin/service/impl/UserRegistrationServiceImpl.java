package org.pkb.springlogin.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pkb.springlogin.dao.UserInfoDAO;
import org.pkb.springlogin.dao.UserRoleDao;
import org.pkb.springlogin.model.*;
import org.pkb.springlogin.service.EmailService;
import org.pkb.springlogin.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

/**
 * Created by Rakshith on 5/1/17.
 */
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserInfoDAO userInfoDAO;
    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    private Environment env;

    @Override
    public ValidationResponse registerUser(UserInfo userInfo, String IPAddress) {
        ValidationResponse validationResponse = new ValidationResponse();
        try {
            //verifying captcha
            URL url = new URL(env.getProperty("verifty-url").
                    replace("{0}",env.getProperty("secret-key")).
                    replace("{1}",userInfo.getReCaptcha()).
                    replace("{2}",IPAddress));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            String line, outputString = "";
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                outputString += line;
            }
            ObjectMapper mapper = new ObjectMapper();
            //mapping captcha response to captchaResponse object using jackson object mapper
            CaptchaResponse captchaResponse = mapper.readValue(outputString, CaptchaResponse.class);
            if (captchaResponse.getSuccess() == "true") {
                //encoding username to base64 format
                String encoded = Base64.getEncoder().encodeToString(userInfo.getUserName().getBytes("utf-8"));
                //insert into users table
                userInfoDAO.insertIntoUsers(userInfo);
                UserRoles userRoles = new UserRoles();
                userRoles.setUserName(userInfo.getUserName());
                userRoles.setUserRole(userInfo.getUserRole());
                //insert into user role table.
                userRoleDao.insertIntoUserRoles(userRoles);
                //sending email.
                EmailProfile emailProfile = new EmailProfile(userInfo.getEmail(), "Please verify your mail",
                        "Hi,<br/> Click to verify email: http://localhost:8080/enableUser?userName=" + encoded);
                emailService.sendEmail(emailProfile);
                validationResponse.setStatus("success");
            } else {
                validationResponse.setStatus("captchaFailed");
            }
        } catch (MalformedURLException e) {
            validationResponse.setStatus("error");
        } catch (IOException e) {
            validationResponse.setStatus("error");
        }
        return validationResponse;
    }

    @Override
    public boolean findUserNameIfExists(String userName) {
        boolean userNameExists = userInfoDAO.getUserNames().stream().anyMatch(name -> name.
                equalsIgnoreCase(userName));
        return userNameExists;
    }

    @Override
    public void enableUser(String userName) {
        userInfoDAO.enableUser(userName);
    }
}
