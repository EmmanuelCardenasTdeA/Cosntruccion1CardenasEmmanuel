package app.domain.models;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MedicalRecord {

    private Date dateCreated;
    private Person veterinarian;
    private String consultationReason;
    private String symptomatology;
    private String diagnostic;
    private String procedure;
    private Order order;
    private String vaccumHistory;
    private String medicineAllergy;
    private String procedureDetail;
    private boolean isActive;

    public MedicalRecord(Date dateCreated, Person veterinarian, String consultationReason, String symptomatology,
            String diagnostic, String procedure, Order order, String vaccumHistory, String medicineAllergy,
            String procedureDetail, boolean isActive) {
        this.dateCreated = dateCreated;
        this.veterinarian = veterinarian;
        this.consultationReason = consultationReason;
        this.symptomatology = symptomatology;
        this.diagnostic = diagnostic;
        this.procedure = procedure;
        this.order = order;
        this.vaccumHistory = vaccumHistory;
        this.medicineAllergy = medicineAllergy;
        this.procedureDetail = procedureDetail;
        this.isActive = isActive;
    }
}
