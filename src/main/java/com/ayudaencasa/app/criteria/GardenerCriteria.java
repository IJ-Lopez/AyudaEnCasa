package com.ayudaencasa.app.criteria;

import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Filtros de búsqueda
public class GardenerCriteria {
    
    private DoubleFilter surface;
    private BooleanFilter tools;
    private BooleanFilter poolCleaning;
    private BooleanFilter gardenFence;
    private BooleanFilter plantDisinfection;

    
    
   
}
