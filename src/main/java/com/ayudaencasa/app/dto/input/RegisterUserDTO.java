package com.ayudaencasa.app.dto.input;

import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegisterUserDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private Integer dni;
    private String address;
    private Long phone;
    private MultipartFile pic;
    // private Cv cv;

}
