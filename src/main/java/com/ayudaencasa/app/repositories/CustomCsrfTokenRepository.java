

package com.ayudaencasa.app.repositories;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.server.csrf.DefaultCsrfToken;
//import org.springframework.security.web.csrf.DefaultCsrfToken;


public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        CsrfToken t = new DefaultCsrfToken(headerName:"X-TOKEN", parameterName:"_csrf" , token:"123456789");
        //CsrfToken t = new DefaultCsrfToken(headerName:"X-CSRF-TOKEN", parameterName:"_csrf", token:"123456789");
       
               
               
        
        return t;  
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        return null;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
