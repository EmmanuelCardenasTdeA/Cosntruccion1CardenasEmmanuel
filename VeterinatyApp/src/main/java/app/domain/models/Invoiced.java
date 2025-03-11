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
    private Pet petId;
    private Person ownerId;
    private Orden ordenId;
    private double amount;
    private long medicationQuantity;
    private Date date;

    public Invoiced(long invoicedId, Pet petId, Person ownerId, Orden ordenId, double amount, long medicationQuantity,
            Date date) {
        this.invoicedId = invoicedId;
        this.petId = petId;
        this.ownerId = ownerId;
        this.ordenId = ordenId;
        this.amount = amount;
        this.medicationQuantity = medicationQuantity;
        this.date = date;
    }
}
