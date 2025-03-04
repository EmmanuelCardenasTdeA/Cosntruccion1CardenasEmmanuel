package app.domain.services;

import java.util.List;

import app.domain.models.ClinicaRecord;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.domain.ports.ClinicalRecordPort;
import app.domain.ports.PersonPort;
import app.domain.ports.PetPort;
import app.domain.ports.UserPort;

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
        user.setPersonDocument(1);
        user.setPersonName("veterinary");
        user.setPersonAge(0);
        user.setUserName("userName");
        user.setPassword("password");
        user.setRole(2);
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
        user.setPersonDocument(12);
        user.setPersonName("seller");
        user.setPersonAge(0);
        user.setUserName("userNameSeller");
        user.setPassword("password");
        user.setRole(3);
        personPort.savePerson(user);
        userPort.saveUser(user);
    }
    
    public void registerOwner(Person person) throws Exception{
        if(personPort.existsPerson(person.getPersonDocument())){
            throw new Exception("Ya existe un dueño con ese nombre");
        }
        person.setPersonDocument(123);
        person.setPersonName("owner");
        person.setPersonAge(0);
        personPort.savePerson(person);
    }

    public void registerPet(Pet pet) throws Exception{

        //Agregar Lógica
        petPort.save(pet);
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
