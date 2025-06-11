package app.adapters.rest.resposive;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRest {
    private long ordenId;
    private PetRest pet;
    private PersonRest owner;
    private PersonRest veterinarian;
    private String medicationName;
    private double medicationDosis;
    private String ordenStatus;
    private Timestamp date;
    @Override
    public String toString() {
        return "OrderRest [ordenId=" + ordenId + ", pet=" + pet + ", owner=" +  ", veterinarian=" + veterinarian
                + ", medicationName=" + medicationName + ", medicationDosis=" + medicationDosis + ", ordenStatus="
                + ordenStatus + ", date=" + date + "]";
    }

    
}
