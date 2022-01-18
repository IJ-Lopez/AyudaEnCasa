package com.ayudaencasa.app.criteria;

import com.ayudaencasa.app.enums.Day;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.RangeFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//Filtros de búsqueda
public class GardenerCriteria {
    
    private DoubleFilter surface;
    private BooleanFilter tools;
    private BooleanFilter poolCleaning;
    private BooleanFilter gardenFence;
    private BooleanFilter plantDisinfection;
    private IntegerFilter salary;
    private StringFilter workingZone;
    private StringFilter description;
    private IntegerFilter hoursFrom;
    private IntegerFilter hoursTo;

}
