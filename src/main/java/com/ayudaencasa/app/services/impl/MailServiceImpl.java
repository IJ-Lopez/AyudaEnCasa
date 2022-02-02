package com.ayudaencasa.app.services.impl;

import com.ayudaencasa.app.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailServiceImpl implements MailService {
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Override
    public void sendMail(String addressee, String subject, String message){
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(addressee);
        smm.setSubject(subject);
        smm.setText(message);
        javaMailSender.send(smm);
    }

    
    
}