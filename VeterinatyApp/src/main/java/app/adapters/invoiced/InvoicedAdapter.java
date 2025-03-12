package app.adapters.invoiced;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import app.adapters.invoiced.entity.InvoicedEntity;
import app.adapters.invoiced.repository.InvoicedRepository;
import app.adapters.orden.entity.OrdenEntity;
import app.adapters.persons.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.adapters.users.entity.UserEntity;
import app.domain.models.Invoiced;
import app.domain.models.Orden;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.InvoicedPort;

public class InvoicedAdapter implements InvoicedPort{
    @Autowired
    private InvoicedRepository invoicedRepository;

    @Override
    public void saveInvoiced(Invoiced invoiced) {
        InvoicedEntity invoicedEntity = invoicedAdapter(invoiced);
        invoicedRepository.save(invoicedEntity);
        invoiced.setInvoicedId(invoicedEntity.getInvoicedId());
        invoiced.setDate(invoicedEntity.getDate());
    }

    @Override
    public List<Invoiced> getAllInvoices() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllInvoices'");
    }

    @Override
    public List<InvoicedEntity> findByPersonDocument(long personDocument) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPersonDocument'");
    }

    @Override
    public List<InvoicedEntity> findByPetId(long petId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPetId'");
    }

    @Override
    public List<InvoicedEntity> findByOrdenId(long ordenId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByOrdenId'");
    }

    private InvoicedEntity invoicedAdapter(Invoiced invoiced){
        InvoicedEntity invoicedEntity = new InvoicedEntity();
        invoicedEntity.setAmount(invoiced.getAmount());
        invoicedEntity.setPet(petAdapter(invoiced.getPet()));
        invoicedEntity.setPerson(personAdapter(invoiced.getPerson()));
        invoicedEntity.setOrden(ordenAdapter(invoiced.getOrden()));
        invoicedEntity.setInvoicedId(invoiced.getInvoicedId());
        invoicedEntity.setAmount(invoiced.getAmount());
        invoicedEntity.setMedicationQuantity(invoiced.getMedicationQuantity());
        invoicedEntity.setDate(invoiced.getDate());
        return invoicedEntity;
    }

    private PetEntity petAdapter(Pet pet){
        PetEntity petEntity = new PetEntity();
        petEntity.setPetId(pet.getPetId());
        petEntity.setPetName(pet.getPetName());
        petEntity.setPetAge(pet.getPetAge());
        petEntity.setPetSpecies(pet.getPetSpecies());
        petEntity.setPetRace(pet.getPetRace());
        petEntity.setPetWeight(pet.getPetWeight());
        return petEntity;
    }

    private PersonEntity personAdapter(Person person){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(person.getPersonName());
        personEntity.setDocument(person.getPersonDocument());
        person.setPersonAge(person.getPersonAge());
        return personEntity;
    }

    private OrdenEntity ordenAdapter(Orden orden){
        OrdenEntity ordenEntity = new OrdenEntity();
        ordenEntity.setPerson(personAdapter(orden.getOwner()));
        ordenEntity.setPet(petAdapter(orden.getPet()));
        ordenEntity.setUser(userAdapter(orden.getVeterinarian()));
        ordenEntity.setOrdenId(orden.getOrdenId());
        ordenEntity.setMedicationName(orden.getMedicationName());
        ordenEntity.setMedicationDosis(orden.getMedicationDosis());
        ordenEntity.setDate(orden.getDate());
        return ordenEntity;
    }

    private Pet petAdapter(PetEntity petEntity) {
        Pet pet = new Pet();
        pet.setPetName(petEntity.getPetName());
        pet.setPetAge(petEntity.getPetAge());
        pet.setPetSpecies(petEntity.getPetSpecies());
        pet.setPetRace(petEntity.getPetRace());
        pet.setPetWeight(petEntity.getPetWeight());
        return pet;
    }

    private Person personAdapter(PersonEntity personEntity){
        Person person = new Person();
        person.setPersonName(personEntity.getName());
        person.setPersonDocument(personEntity.getDocument());
        person.setPersonAge(personEntity.getAge());
        return person;
    }

    private Orden ordenAdapter(OrdenEntity ordenEntity){
        Orden orden = new Orden();
        orden.setOrdenId(ordenEntity.getOrdenId());
        orden.setOwner(personAdapter(ordenEntity.getPerson()));
        orden.setPet(petAdapter(ordenEntity.getPet()));
        orden.setVeterinarian(userAdapter(ordenEntity.getUser()));
        orden.setMedicationName(ordenEntity.getMedicationName());
        orden.setMedicationDosis(ordenEntity.getMedicationDosis());
        orden.setDate(ordenEntity.getDate());
        return orden;
    }



    private Invoiced invoicedAdapter(InvoicedEntity invoicedEntity){
        Invoiced invoiced = new Invoiced();
        invoiced.setInvoicedId(invoicedEntity.getInvoicedId());
        invoiced.setOrden(ordenAdapter(invoicedEntity.getOrden()));
        invoiced.setPerson(personAdapter(invoicedEntity.getPerson()));
        invoiced.setPet(petAdapter(invoicedEntity.getPet()));
        invoiced.setAmount(invoicedEntity.getAmount());
        invoiced.setMedicationQuantity(invoicedEntity.getMedicationQuantity());
        invoiced.setDate(invoicedEntity.getDate());
        return invoiced;
    }
    

    private User userAdapter(UserEntity userEntity){
        User user = new User();
        user.setUserId(userEntity.getUserId());
        user.setPersonName(userEntity.getPerson().getName());
        user.setPersonDocument(userEntity.getPerson().getDocument());
        user.setPersonAge(userEntity.getPerson().getAge());
        user.setUserName(userEntity.getUserName());
        return user;
    }

    private UserEntity userAdapter(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId());
        userEntity.setPerson(personAdapter(user));
        return userEntity;
    }


}
