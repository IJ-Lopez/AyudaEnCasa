
package com.ayudaencasa.app.dto.input;

import java.time.LocalTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class CreatePetWalkerDTO {
    
    private Integer petQuantity;
    private String petType;
    private String petRace;
    private Integer salary;
    private String workingZone;
    private String description;
    private List<String> days;
    private LocalTime workingHoursFrom;
    private LocalTime workingHoursTo;
    private Integer hoursFrom;
    private Integer hoursTo;
    private Boolean status;
    private MultipartFile cv;

    
    
    
    
    
    
}
