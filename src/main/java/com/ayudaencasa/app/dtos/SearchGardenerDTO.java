package com.ayudaencasa.app.dtos;



import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchGardenerDTO {
    
    private Double surfaceFrom;
    private Double surfaceTo; 
    private String tools;
    private String poolCleaning;
    private String gardenFence;
    private String plantDisinfection;
    private Integer salaryFrom;
    private Integer salaryTo;
    private String workingZone;
    private String description;
    
    private String day;

    private LocalTime workingHoursFrom;

    private LocalTime workingHoursTo;
    
    private Integer hoursFrom;
    private Integer hoursTo;
    
 
}
