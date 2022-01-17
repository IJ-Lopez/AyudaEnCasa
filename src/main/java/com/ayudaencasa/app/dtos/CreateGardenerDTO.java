/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.dtos;

import com.ayudaencasa.app.enums.Day;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import java.time.LocalTime;




import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;


@Data
@NoArgsConstructor
public class CreateGardenerDTO {
    
    private Double surface;
    
    private Boolean tools;
    
    private Boolean poolCleaning;
    
    private Boolean gardenFence;
    
    private Boolean plantDisinfection;
    
//    @NotNull
//    @NonNull
    private Integer salary;
    
    private String workingZone;
    
    private String description;
    
    private List<String> days;
    
//    @Temporal(value = TemporalType.TIME)
//    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
//    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime workingHoursFrom;
   
//    @Temporal(value = TemporalType.TIME)
//    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
//    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime workingHoursTo;
    
//    @NotNull
//    @NonNull
    
    private Integer hoursFrom;
    
//    @NotNull
//    @NonNull
    
    private Integer hoursTo;
    
    private Boolean status;
 
}
