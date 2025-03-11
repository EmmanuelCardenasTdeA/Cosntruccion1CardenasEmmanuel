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
    private User veterinarian;
    private String medicationName;
    private double medicationDosis;
    private String ordenStatus;
    private Date date;

    public Orden(long ordenId, Pet pet, Person owner, User veterinarian, String medicationName,
    String ordenStatus, double medicationDosis, Date date) {
        this.ordenId = ordenId;
        this.pet = pet;
        this.owner = owner;
        this.veterinarian = veterinarian;
        this.medicationName = medicationName;
        this.medicationDosis = medicationDosis;
        this.ordenStatus = ordenStatus;
        this.date = date;
    }

}
