package com.ayudaencasa.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
