package com.ayudaencasa.app.services;


public interface MailService {
    
    public void sendMail(String email, String subject, String message);
    
}