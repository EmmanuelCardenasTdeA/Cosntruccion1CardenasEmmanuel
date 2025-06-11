package app.adapters.rest.resposive;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClinicalRest {
    private long clinicaId;
    private Timestamp date;
    private String consultation;
    private String syntomatology;
    private String diagnostic;
    private String treatment;
    private OrderRest orden;
    private String vacumHistory;
    private String allergyMedicines;
    private String detailsTreatement;
}
