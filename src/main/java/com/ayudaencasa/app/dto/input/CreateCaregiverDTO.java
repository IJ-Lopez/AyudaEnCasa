
package com.ayudaencasa.app.dto.input;

import java.time.LocalTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
public class CreateCaregiverDTO {
    
    private Integer salary;
    private String workingZone;
    private String description;
    private List<String> days;
    private LocalTime workingHoursFrom;
    private LocalTime workingHoursTo;
    private Integer hoursFrom;
    private Integer hoursTo;
    private Boolean status;
    private Integer quantity;    
//    private Integer ageFrom;   
//    private Integer ageTo;
    private String ageRange;
    private MultipartFile cv;
    private String pic;
    private boolean cooking; 
    private boolean cleaningPeople;
    private boolean tranfering;
    
}
