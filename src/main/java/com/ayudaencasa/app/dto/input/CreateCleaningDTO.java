package com.ayudaencasa.app.dto.input;

import java.time.LocalTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@NoArgsConstructor
@Data
public class CreateCleaningDTO {
    
    private String id;
    private Integer rooms;
    private String exteriors;
    private boolean cooking;
    private boolean laundry;
    private boolean ironing;    
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
