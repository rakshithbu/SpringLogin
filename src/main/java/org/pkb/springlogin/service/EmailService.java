package org.pkb.springlogin.service;

import org.pkb.springlogin.model.EmailProfile;
import org.springframework.stereotype.Service;

/**
 * Created by Rakshith on 7/1/17.
 */
@Service
public interface EmailService {
    void sendEmail(EmailProfile emailProfile);
}
