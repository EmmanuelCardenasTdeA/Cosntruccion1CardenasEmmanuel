package app.domain.services;

import java.util.List;

import app.domain.models.ClinicaRecord;
import app.domain.models.User;
import app.domain.ports.ClinicalRecordPort;

public class VeterinaryServices {

    private ClinicalRecordPort clinicalRecordPort;

    public List<ClinicaRecord> getClinicaRecord(User user) throws Exception{
        if(user == null){
            return clinicalRecordPort.getAllClinicalRecord();
        }
        if(user.getRole() != 2){
            throw new Exception("El usuario no es un veterinario");
        }
        
        return clinicalRecordPort.getAllClinicalRecordByPerson(user);

    }
}
