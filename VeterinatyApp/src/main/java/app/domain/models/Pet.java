package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Pet{
    
    private String petName;
    private Person owner;
    private long petAge;
    private long petId;
    private String petRace;
    private double petWeight;
    
    public Pet(String petName, Person owner, long petAge, long petId, String petRace, double petWeight) {
        this.petName = petName;
        this.owner = owner;
        this.petAge = petAge;
        this.petId = petId;
        this.petRace = petRace;
        this.petWeight = petWeight;
    }
  
}
