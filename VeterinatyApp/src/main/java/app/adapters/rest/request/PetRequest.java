package app.adapters.rest.request;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class PetRequest {
    private long ownerDocument;
    private String petName;
    private long petAge;
    private long petId;
    private String petSpecies;
    private String petRace;
    private double petWeight;

}
