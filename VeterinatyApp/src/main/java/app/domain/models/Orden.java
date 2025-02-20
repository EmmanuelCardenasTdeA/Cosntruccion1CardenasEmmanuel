package app.domain.models;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Orden {
    private long ordenId;
    private Pet pet;
    private Person owner;
    private Person veterinarian;
    private String medicationName;
    private double medicationDosis;
    private Date date;

    public Orden(long ordenId, Pet pet, Person owner, Person veterinarian, String medicationName,
            double medicationDosis, Date date) {
        this.ordenId = ordenId;
        this.pet = pet;
        this.owner = owner;
        this.veterinarian = veterinarian;
        this.medicationName = medicationName;
        this.medicationDosis = medicationDosis;
        this.date = date;
    }

}
