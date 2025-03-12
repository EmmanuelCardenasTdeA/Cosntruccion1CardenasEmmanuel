package app.domain.models;


import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor

public class Invoiced {
    private long invoicedId;
    private Pet pet;
    private Person person;
    private Orden orden;
    private double amount;
    private long medicationQuantity;
    private Timestamp date;

    public Invoiced(long invoicedId, Pet pet, Person person, Orden orden, double amount, long medicationQuantity,
        Timestamp date) {
        this.invoicedId = invoicedId;
        this.pet = pet;
        this.person = person;
        this.orden = orden;
        this.amount = amount;
        this.medicationQuantity = medicationQuantity;
        this.date = date;
    }
}
