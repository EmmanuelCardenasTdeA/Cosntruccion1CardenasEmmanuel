package app.domain.models;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor

public class Invoiced {
    private String orderId;
    private Pet pet;
    private Person petOwener;
    private Order order;
    private String product;
    private Double amount;
    private Long quantity;
    private Date date;

    public Invoiced(String orderId, Pet pet, Person petOwener, Order order, String product, Double amount, Long quantity, Date date) {
        this.orderId = orderId;
        this.pet = pet;
        this.petOwener = petOwener;
        this.order = order;
        this.product = product;
        this.amount = amount;
        this.quantity = quantity;
        this.date = date;
    }

}
