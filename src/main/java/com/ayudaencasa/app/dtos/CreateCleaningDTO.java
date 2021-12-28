package com.ayudaencasa.app.dtos;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
public class CreateCleaningDTO {

    @NonNull
    @NotNull
    private Integer rooms;
    
    private Boolean exteriors;
    
    private Boolean cooking;
    
    private Boolean laundry;
    
    private Boolean ironing;
    
    @NonNull
    @NotNull
    private Integer salary;
    
    @NonNull
    @NotNull
    private String workingZone;
    
    private String description;
    
    @NonNull
    @NotNull
    private Date dateFrom;
    
    @NonNull
    @NotNull
    private Date dateTo;
    
    private Boolean status;
}
