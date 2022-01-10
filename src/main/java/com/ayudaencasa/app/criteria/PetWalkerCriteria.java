
package com.ayudaencasa.app.criteria;

/**
 *
 * @author martina
 */
//filtros de busqueda
public class PetWalkerCriteria {
private IntegerFilter petQuantity;
private StringFilter petType;
private StringFilter petRace;

    public PetWalkerCriteria() {
    }

public IntegerFilter getPetQuantity(){
    return petQuantity;
}
public void setPetQuantity (IntegerFilter petQuantity){
     this.petQuantity = petQuantity;
}

public StringFilter getpetRace(){
    return petRace;
}
public void setPetQuantity (StringFilter petRace){
     this.petRace = petRace;
}

public StringFilter getpetType(){
    return petType;
}
public void setpetType (StringFilter petType){
     this.petType = petType;
}
}
