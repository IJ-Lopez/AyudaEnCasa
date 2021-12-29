/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.criteria;

import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Usr
 */
//Filtros de búsqueda
@Getter
@Setter
public class GardenerCriteria {
    
    private DoubleFilter surface;
    private BooleanFilter tools;
    private BooleanFilter poolCleaning;
    private BooleanFilter gardenFence;
    private BooleanFilter plantDisinfection;

    
    
   
}
