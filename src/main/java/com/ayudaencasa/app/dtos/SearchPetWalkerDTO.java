/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.dtos;

/**
 *
 * @author martina
 */
public class SearchPetWalkerDTO {
    
    private Integer petQuantityFrom;
    private Integer petQuantityTo;
    private String petType;
    private String petRace;        
    

    public Integer getPetQuantityFrom() {
        return petQuantityFrom;
    }

    public void setPetQuantityFrom(Integer petQuantityFrom) {
        this.petQuantityFrom = petQuantityFrom;
    }

    public Integer getPetQuantityTo() {
        return petQuantityTo;
    }

    public void setPetQuantityTo(Integer petQuantityTo) {
        this.petQuantityTo = petQuantityTo;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetRace() {
        return petRace;
    }

    public void setPetRace(String petRace) {
        this.petRace = petRace;
    }

}
