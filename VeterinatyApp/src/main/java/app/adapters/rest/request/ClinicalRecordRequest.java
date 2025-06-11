package app.adapters.rest.request;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ClinicalRecordRequest {
    private String consultation;
    private String syntomatology;
    private String diagnostic;
    private String treatment;
    private long orderId;
    private String vacumHistory;
    private String allergyMedicines;
    private String detailsTreatement;  
}
