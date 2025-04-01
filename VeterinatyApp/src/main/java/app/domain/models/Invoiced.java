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
    private Orden orden;
    private double amount;
    private long medicationQuantity;
    private String product;
    private Timestamp date;

    public Invoiced(long invoicedId, Pet pet, Person person, Orden orden, double amount, long medicationQuantity,String product,
        Timestamp date) {
        this.invoicedId = invoicedId;
        this.orden = orden;
        this.amount = amount;
        this.medicationQuantity = medicationQuantity;
        this.product = product;
        this.date = date;
    }
}
