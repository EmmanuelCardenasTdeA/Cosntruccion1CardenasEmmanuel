package app.domain.models;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor

public class ClinicaRecord {
    private Date date;
    private Person veterinary;
    private String cosultation;
    private String syntomatology;
    private String diagnostic;
    private String treatment;
    private Orden orden;
    private List<String> vacumHistory;
    private List<String> allergyMedicines;
    private String detailsTreatement;
    private boolean isActiva;

    public ClinicaRecord(Date date, Person veterinary, String cosultation, String syntomatology, String diagnostic,
            String treatment, Orden orden, List<String> vacumHistory, List<String> allergyMedicines,
            String detailsTreatement, boolean isActiva) {
        this.date = date;
        this.veterinary = veterinary;
        this.cosultation = cosultation;
        this.syntomatology = syntomatology;
        this.diagnostic = diagnostic;
        this.treatment = treatment;
        this.orden = orden;
        this.vacumHistory = vacumHistory;
        this.allergyMedicines = allergyMedicines;
        this.detailsTreatement = detailsTreatement;
        this.isActiva = isActiva;
    }
    

}
