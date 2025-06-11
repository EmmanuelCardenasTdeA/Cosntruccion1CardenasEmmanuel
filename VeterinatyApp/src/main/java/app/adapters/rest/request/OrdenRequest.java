package app.adapters.rest.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrdenRequest {
    private long ordenId;
    private long petId;
    private long ownerDocument;
    private long veterinarianId;
    private String medicationName;
    private double medicationDosis;
    private String ordenStatus;
}
