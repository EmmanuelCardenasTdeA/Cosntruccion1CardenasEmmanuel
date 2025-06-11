package app.adapters.rest.resposive;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class InvoicedRest {
    private long invoicedId;
    private OrderRest orden;
    private double amount;
    private long medicationQuantity;
    private String product;
    private Timestamp date;
}
