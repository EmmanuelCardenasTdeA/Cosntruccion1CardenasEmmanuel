package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Pet{
    /*private Person ownerName; */
    private Person personId;
    private String petName;
    private long petAge;
    private long petId;
    private String petSpecies;
    private String petRace;
    private double petWeight;
    
    public Pet(Person personId, String petName, long petAge, long petId, String petSpecies, String petRace, double petWeight) {
        this.personId = personId;
        this.petName = petName;
        this.petAge = petAge;
        this.petId = petId;
        this.petSpecies = petSpecies;
        this.petRace = petRace;
        this.petWeight = petWeight;
    }
}
