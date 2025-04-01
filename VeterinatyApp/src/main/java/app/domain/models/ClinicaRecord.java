package app.domain.models;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor

public class ClinicaRecord {
    private long clinicaId;
    private Timestamp date;
    private String consultation;
    private String syntomatology;
    private String diagnostic;
    private String treatment;
    private Orden orden;
    private String vacumHistory;
    private String allergyMedicines;
    private String detailsTreatement;

    public ClinicaRecord(long clinicaId,Timestamp date, String consultation, String syntomatology, String diagnostic,
            String treatment, Orden orden, String vacumHistory, String allergyMedicines,
            String detailsTreatement) {
        this.clinicaId = clinicaId;
        this.date = date;
        this.consultation = consultation;
        this.syntomatology = syntomatology;
        this.diagnostic = diagnostic;
        this.treatment = treatment;
        this.orden = orden;
        this.vacumHistory = vacumHistory;
        this.allergyMedicines = allergyMedicines;
        this.detailsTreatement = detailsTreatement;
    }
}
