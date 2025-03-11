package app.domain.services;

import java.util.List;

import app.domain.models.ClinicaRecord;
import app.domain.models.Orden;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.ClinicalRecordPort;
import app.ports.OrdenPort;
import app.ports.PersonPort;
import app.ports.PetPort;
import app.ports.UserPort;


public class VeterinaryServices {

    private ClinicalRecordPort clinicalRecordPort;
    private ClinicaRecord clinicalRecord;
    private ClinicaRecordServices clinicalRecordServices;
    private PetPort petPort;
    private PersonPort personPort;
    private UserPort userPort;
    private OrdenPort ordenPort;
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
        
        clinicalRecord.setActiva(true);
        clinicalRecordServices.saveClinicalRecord(clinicalRecord);
    }

    public void registerPet(Pet pet) throws Exception{
        //Agregar lógica
        Person person = personPort.findByPersonDocument(pet.getPersonId().getPersonDocument());
        if(person == null){
            throw new Exception("No existe una persona con ese documetno");
        }
        
        pet.setPersonId(person);
        petPort.savePet(pet);
    }
    
    public void registerClient(Person person)throws Exception{
        if(personPort.existsPerson(person.getPersonDocument())){
            throw new Exception("Ya existe una Ciente con esa cedula");
        }
        personPort.savePerson(person);
        System.out.println("Cliente Creado");
    }
    public void registerOrden(Orden orden) throws Exception{
        Pet pet = petPort.findByPetId(orden.getPet().getPetId());
        Person person = personPort.findByPersonDocument(orden.getOwner().getPersonDocument());
        User user = userPort.findByPersonDocument(orden.getVeterinarian().getPersonDocument());
        if(pet == null){
            throw new Exception("No existe mascota con ese ID");
        }
        if(person == null){
            throw new Exception("No existe cliente con esa cedula"); 
        }
        if(user == null){
            throw new Exception("No existe veterinario con esa cedula");
        }
        orden.setPet(pet);
        orden.setOwner(person);
        orden.setVeterinarian(user);
        ordenPort.saveOrden(orden);
    }
}
