package app.adapters.rest.request;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class InvoicedRequest {
    private long orderId;
    private double amount;
    private long medicationQuantity;
    private String product;
}
