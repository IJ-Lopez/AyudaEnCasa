/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.dtos;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class CreateGardenerDTO {
    
    private Double surface;
    
    private Boolean tools;
    
    private Boolean poolCleaning;
    
    private Boolean gardenFence;
    
    private Boolean plantDisinfection;
    
    @NotNull
    @NonNull
    private Integer salary;
    
    private String workingZone;
    
    private String description;
    
    @NotNull
    @NonNull
    private Date dateFrom;
    
    @NotNull
    @NonNull
    private Date dateTo;
    
    private Boolean status;
    
}
