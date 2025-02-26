package app.domain.services;

import java.util.List;

import app.domain.models.ClinicaRecord;
import app.domain.models.Pet;
import app.domain.models.User;
import app.domain.ports.ClinicalRecordPort;
import app.domain.ports.PersonPort;
import app.domain.ports.PetPort;

public class VeterinaryServices {

    private ClinicalRecordPort clinicalRecordPort;
    private ClinicaRecord clinicalRecord;
    private ClinicaRecordServices clinicalRecordServices;
    private PetPort petPort;
    private PersonPort personPort;

    public List<ClinicaRecord> getClinicaRecord(User user) throws Exception{
        if(user == null){
            return clinicalRecordPort.getAllClinicalRecord();
        }
        if(user.getRole() != 2){
            throw new Exception("El usuario no es un veterinario");
        }
        
        return clinicalRecordPort.getAllClinicalRecordByPerson(user);

    }

    public void createClinicalRecord(User user)throws Exception{
        if(user.getRole() != 2){
            throw new Exception("El usuario no es un veterinario");
        }
        
        clinicalRecord.setCosultation("Consulta");
        clinicalRecord.setSyntomatology("Sintomatologia");
        clinicalRecord.setDiagnostic("Diagnostico");
        clinicalRecord.setTreatment("Tratamiento");
        clinicalRecord.setOrden(null);
        clinicalRecord.setVacumHistory(null);
        clinicalRecord.setAllergyMedicines(null);
        clinicalRecord.setDetailsTreatement("Detalles del tratamiento");
        clinicalRecord.setActiva(true);
        clinicalRecordServices.saveClinicalRecord(clinicalRecord);
    }

        public void registerPet(Pet pet) throws Exception{
        if(!petPort.existsPerson(pet.getOwner().getPersonDocument())){
            throw new Exception("La mascota no tiene dueño");
        }
        pet.setPetName("petName2");
        pet.setOwner(personPort.findByDocument(123));
        pet.setPetAge(0);
        pet.setPetRace("petRace2");
        pet.setPetWeight(1231.123);
        petPort.savePet(pet);
    }
}
