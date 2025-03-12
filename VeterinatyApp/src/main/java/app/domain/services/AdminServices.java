package app.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;

import app.domain.models.ClinicaRecord;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.ClinicalRecordPort;
import app.ports.PersonPort;
import app.ports.PetPort;
import app.ports.UserPort;

@Service
public class AdminServices {
    private ClinicalRecordPort clinicalRecordPort;
    private PersonPort personPort;
    private UserPort userPort;
    private PetPort petPort;
    public void registerVeterinarian(User user)throws Exception{
        if(personPort.existsPerson(user.getPersonDocument())){
            throw new Exception("Ya existe un veterinario con ese nombre");
        }
        if(userPort.existUserName(user.getUserName())){
            throw new Exception("Ya existe un usuario con ese nombre");
        }
        personPort.savePerson(user);
        userPort.saveUser(user);
    }

    public void registerSeller(User user)throws Exception{
        if(personPort.existsPerson(user.getPersonDocument())){
            throw new Exception("Ya existe un vendedor con ese nombre");
        }
        if(userPort.existUserName(user.getUserName())){
            throw new Exception("Ya existe un usuario con ese nombre");
        }
        personPort.savePerson(user);
        userPort.saveUser(user);
    }
    
    public void registerOwner(Person person) throws Exception{
        if(personPort.existsPerson(person.getPersonDocument())){
            throw new Exception("Ya existe un cliente con ese documento");
        }
        
        personPort.savePerson(person);
    }

    public void registerPet(Pet pet) throws Exception{
        Person person = personPort.findByPersonDocument(pet.getPersonId().getPersonDocument());
        if(person == null){
            System.out.println("No existe un cliente con ese documento");
            throw new Exception("No existe una persona con ese documetno");
        }
        
        pet.setPersonId(person);
        petPort.savePet(pet);
    }

     public List<ClinicaRecord> getClinicaRecord(User user) throws Exception{
        if(user == null){
            return clinicalRecordPort.getAllClinicalRecord();
        }
        
        if(user.getRole() != 1){
            throw new Exception("El usuario no es administrador");
        }
        
        return clinicalRecordPort.getAllClinicalRecordByPerson(user);

    }
}
