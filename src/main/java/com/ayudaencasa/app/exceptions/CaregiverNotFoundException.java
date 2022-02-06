/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.exceptions;

public class CaregiverNotFoundException extends RuntimeException{
    
    public CaregiverNotFoundException() {
        super("Caregiver was not found");
    }

    public CaregiverNotFoundException(String msg) {
        super(msg);
    }
    
}
