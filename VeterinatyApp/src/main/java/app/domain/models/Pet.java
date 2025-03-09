package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Pet{
    /*private Person ownerName; */
    private Person person;
    private String petName;
    private long petAge;
    private long petId;
    private String petRace;
    private double petWeight;
    
    public Pet(Person person, String petName, long petAge, long petId, String petRace, double petWeight) {
        this.person = person;
        this.petName = petName;
        this.petAge = petAge;
        this.petId = petId;
        this.petRace = petRace;
        this.petWeight = petWeight;
    }  
}
