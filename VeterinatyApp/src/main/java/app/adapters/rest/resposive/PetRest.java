package app.adapters.rest.resposive;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PetRest {
    private String petName;
    private long petAge;
    private long petId;
    private String petSpecies;
    private String petRace;
    private double petWeight;
}
