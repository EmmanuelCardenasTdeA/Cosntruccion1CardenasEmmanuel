package app.domain.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.inputs.AdminInput;
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

@Service
public class AdminServices{

    @Autowired
    private PersonPort personPort;
    @Autowired
    private UserPort userPort;
    @Autowired
    private PetPort petPort;
    @Autowired
    private OrdenPort ordenPort;
    @Autowired
    private ClinicalRecordPort clinicalRecordPort;

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
            throw new Exception("No existe una persona con ese documetno");
        }
        pet.setPersonId(person);
        petPort.savePet(pet);
    }

    public void registerOrden(Orden orden)throws Exception{
        Pet pet = petPort.findByPetId(orden.getPet().getPetId());
        Person person = personPort.findByPersonDocument(orden.getOwner().getPersonDocument());
        User user = userPort.findByPersonDocument(orden.getVeterinarian().getPersonDocument());
        if(pet == null){
            throw new Exception("No existe una mascota con ese documento");
        }
        if(person == null){
            throw new Exception("No existe un cliente con ese documetno");
        }
        if(user == null){
            throw new Exception("No existe un veterinario con ese documetno");
        }
        if(!user.getRole().equalsIgnoreCase("veterinary")){
            throw new Exception("El usuario no es un veterinario");
        }
        orden.setPet(pet);
        orden.setOwner(person);
        orden.setVeterinarian(user);
        ordenPort.saveOrden(orden);
    }
 
    public Orden getOrdenByOrdenId(long ordenId) throws Exception{
        Orden orden = ordenPort.findByOrdenId(ordenId);
        if(orden == null){
            throw new Exception("No existe una orden con ese ID");
        }
        return orden;
    }

    public void registerClinicaRecord(ClinicaRecord clinicaRecord) throws Exception {
        Orden orden = ordenPort.findByOrdenId(clinicaRecord.getOrden().getOrdenId());
        if(orden == null){
            throw new Exception("No existe una orden con ese documento");
        }
        clinicaRecord.setOrden(orden);
        clinicalRecordPort.saveClinicaRecord(clinicaRecord);
    }

  public ClinicaRecord getClinicalRecordByClinicaId(long clinicaId) throws Exception{
        ClinicaRecord clinicaRecord = clinicalRecordPort.getClinicaRecordByClnicaId(clinicaId);
        if(clinicaRecord == null){
            throw new Exception("No existe una historia clinica con ese ID");
        }
        return clinicaRecord;
    }

   public  List<Orden> getAllOrdenes() throws Exception {
        return ordenPort.getAllOrden();
    }

    public List<ClinicaRecord> getAllClinicaRecords() throws Exception {
        return clinicalRecordPort.getAllClinicaRecord();
    }
}
