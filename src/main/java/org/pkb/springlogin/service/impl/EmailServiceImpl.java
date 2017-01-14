package org.pkb.springlogin.service.impl;

import org.pkb.springlogin.model.EmailProfile;
import org.pkb.springlogin.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * Created by Rakshith on 7/1/17.
 */
@Service
@Lazy
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Async
    public void sendEmail(EmailProfile emailProfile) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(mailSender.getHost());
            messageHelper.setTo(emailProfile.getTo());
            messageHelper.setSubject(emailProfile.getSubject());
            messageHelper.setText(emailProfile.getBody(), true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
