package com.ayudaencasa.app.dto.input;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
public class RegisterUserDTO {

    @NotNull
    private String email;
    private String password;
    private String password2;
    private String firstName;
    private String lastName;
    private String departament;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private Integer dni;
    private String address;
    private Long phone;
    private MultipartFile pic;
    // private Cv cv;

}
