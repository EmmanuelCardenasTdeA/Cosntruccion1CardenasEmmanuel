package app.domain.models;

import java.sql.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class Order {
    private Long orderId;
    private Pet pet;
    private Person petOwner;
    private Person veterinarian;
    private String medicine;
    private String dosis;
    private Date date;

    public Order(Long orderId, Pet pet, Person petOwner, Person veterinarian, String medicine, String dosis, Date date) {
        this.orderId = orderId;
        this.pet = pet;
        this.petOwner = petOwner;
        this.veterinarian = veterinarian;
        this.medicine = medicine;
        this.dosis = dosis;
        this.date = date;
    }
}
