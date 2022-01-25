/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.dto.input;

import java.time.LocalTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateGardenerDTO {

    private String surface;
    private boolean tools;
    private boolean poolCleaning;
    private boolean gardenFence;
    private boolean plantDisinfection;
    private Integer salary;
    private String workingZone;
    private String description;
    private List<String> days;
    private LocalTime workingHoursFrom;
    private LocalTime workingHoursTo;
    private Integer hoursFrom;
    private Integer hoursTo;
    private Boolean status;
    

}
