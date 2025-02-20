package app.domain.models;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor

public class Invoiced {
    private long invoicedId;
    private Pet pet;
    private Person owner;
    private Orden orden;
    private double amout;
    private long medicationQuantity;
    private Date date;

    public Invoiced(long invoicedId, Pet pet, Person owner, Orden orden, double amout, long medicationQuantity,
            Date date) {
        this.invoicedId = invoicedId;
        this.pet = pet;
        this.owner = owner;
        this.orden = orden;
        this.amout = amout;
        this.medicationQuantity = medicationQuantity;
        this.date = date;
    }
}
