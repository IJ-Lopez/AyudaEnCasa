package com.ayudaencasa.app.services;


public interface MailService {
    
    public void sendMail(String addressee, String subject, String message);
    
}
