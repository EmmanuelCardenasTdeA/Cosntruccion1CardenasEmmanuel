package app.ports;

import java.util.List;

import app.domain.models.ClinicaRecord;
import app.domain.models.Person;
import app.domain.models.Pet;

public interface ClinicalRecordPort {
    public List<ClinicaRecord> getAllClinicalRecord();

    public List<ClinicaRecord> getAllClinicalRecordByPerson(Person person);

    
}
