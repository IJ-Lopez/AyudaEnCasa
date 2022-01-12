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
public class CreatePetWalkerDTO {
    
    private Integer petQuantity;
    private String petType;
    private String petRace;

    public CreatePetWalkerDTO() {
    }

    public CreatePetWalkerDTO(Integer petQuantity, String petType, String petRace) {
        this.petQuantity = petQuantity;
        this.petType = petType;
        this.petRace = petRace;
    }

    public Integer getPetQuantity() {
        return petQuantity;
    }

    public void setPetQuantity(Integer petQuantity) {
        this.petQuantity = petQuantity;
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
