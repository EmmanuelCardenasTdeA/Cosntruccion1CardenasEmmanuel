package app.domain.ports;

import java.util.List;

import app.domain.models.ClinicaRecord;
import app.domain.models.Person;

public interface ClinicalRecordPort {
    public List<ClinicaRecord> getAllClinicalRecord();

    public List<ClinicaRecord> getAllClinicalRecordByPerson(Person person);

    
}
