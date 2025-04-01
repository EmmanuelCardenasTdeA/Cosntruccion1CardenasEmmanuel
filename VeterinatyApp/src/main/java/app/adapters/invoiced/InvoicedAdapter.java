package app.adapters.invoiced;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
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
    public Invoiced findByInvoicedId(long invoicedId) throws Exception{
        InvoicedEntity invoicedEntity = invoicedRepository.findByInvoicedId(invoicedId);
        if(invoicedEntity == null){
            throw new Exception("No existe una fatura con ese Id");
        }
        return invoicedAdapter(invoicedEntity);
    }

    private InvoicedEntity invoicedAdapter(Invoiced invoiced){
        InvoicedEntity invoicedEntity = new InvoicedEntity();
        invoicedEntity.setAmount(invoiced.getAmount());
        invoicedEntity.setOrden(ordenAdapter(invoiced.getOrden()));
        invoicedEntity.setInvoicedId(invoiced.getInvoicedId());
        invoicedEntity.setProduct(invoiced.getProduct());
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
        ordenEntity.setOrdenStatus(orden.getOrdenStatus());
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
        orden.setOrdenStatus(ordenEntity.getOrdenStatus());
        return orden;
    }



    private Invoiced invoicedAdapter(InvoicedEntity invoicedEntity){
        Invoiced invoiced = new Invoiced();
        invoiced.setInvoicedId(invoicedEntity.getInvoicedId());
        invoiced.setOrden(ordenAdapter(invoicedEntity.getOrden()));
        invoiced.setProduct(invoicedEntity.getProduct());
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

    @Override
    public List<Invoiced> getAllInvoiced() throws Exception {
        List<InvoicedEntity> invoicedEntityList = invoicedRepository.findAll();
        if(invoicedEntityList.isEmpty()){
            throw new Exception("No hay facturas");
        }
        return invoicedAdapterList(invoicedEntityList);
    }

    public List<Invoiced> invoicedAdapterList(List<InvoicedEntity> invoicedEntityList)throws Exception{
        List<Invoiced> invoicedList = new ArrayList<>();
        for(InvoicedEntity invoicedEntity : invoicedEntityList){
            invoicedList.add(invoicedAdapter(invoicedEntity));
        }
        return invoicedList;
    }


}
