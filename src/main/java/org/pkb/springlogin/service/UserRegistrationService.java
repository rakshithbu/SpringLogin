package org.pkb.springlogin.service;

import org.pkb.springlogin.model.UserInfo;
import org.pkb.springlogin.model.ValidationResponse;

/**
 * Created by Rakshith on 5/1/17.
 */


public interface UserRegistrationService {
    ValidationResponse registerUser(UserInfo userInfo, String IPAddress);

    boolean findUserNameIfExists(String userName);

    void enableUser(String userName);
}
