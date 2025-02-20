package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class Pet {
    private String petName;
    private long ownerId;
    private long age;
    private String species;
    private String rece;
    private String petDetails;
    private double weight;

    public Pet(String petName, long ownerId, long age, String species, String rece, String petDetails, double weight) {
        this.petName = petName;
        this.ownerId = ownerId;
        this.age = age;
        this.species = species;
        this.rece = rece;
        this.petDetails = petDetails;
        this.weight = weight;
    }

}
