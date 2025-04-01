package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class VeterinaryServices {
    @Autowired
    private ClinicalRecordPort clinicalRecordPort;
    @Autowired
    private PetPort petPort;
    @Autowired
    private PersonPort personPort;
    @Autowired
    private UserPort userPort;
    @Autowired
    private OrdenPort ordenPort;

    public void registerPet(Pet pet) throws Exception{
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

    public void cancelOrden(Orden orden)throws Exception {
        if(orden == null){
            throw new Exception("No existe una orden con ese ID");
        }
        orden.setOrdenStatus("Anulada");
        ordenPort.saveOrden(orden);
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

    public void updateClinicaRecord(ClinicaRecord clinicaRecord)throws Exception {
        clinicalRecordPort.saveClinicaRecord(clinicaRecord);
    }

    public void updateOrden(Orden orden)throws Exception {
        ordenPort.saveOrden(orden);
    }

}
