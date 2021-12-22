
package com.ayudaencasa.app.exceptions;


public class PetWalkerNotFoundException extends RunTimeException {
    
    public PetWalkerNotFoundException(){
        super ("Pet Walker was not found");
    }
   
    public PetWalkerNotFoundException (String msn){
        super (msn);
    }
    
}
