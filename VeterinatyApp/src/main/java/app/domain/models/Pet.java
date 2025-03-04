package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Pet{
    
    private String ownerName;
    private long ownerDocument;
    private String petName;
    private long petAge;
    private long petId;
    private String petRace;
    private double petWeight;
    
    public Pet(String ownerName, long ownerDocument, String petName, long petAge, long petId, String petRace,
            double petWeight) {
        this.ownerName = ownerName;
        this.ownerDocument = ownerDocument;
        this.petName = petName;
        this.petAge = petAge;
        this.petId = petId;
        this.petRace = petRace;
        this.petWeight = petWeight;
    }
    

}
