package app.adapters.orden;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.Exceptions.BusinessException;
import app.adapters.orden.entity.OrdenEntity;
import app.adapters.orden.repository.OrdenRepository;
import app.adapters.persons.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.adapters.users.entity.UserEntity;
import app.domain.models.Orden;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.OrdenPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class OrdenAdapter implements OrdenPort{
    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public void saveOrden(Orden orden) {
        OrdenEntity ordenEntity = ordenAdapter(orden);
        ordenRepository.save(ordenEntity);
        orden.setOrdenId(ordenEntity.getOrdenId());
        orden.setDate(ordenEntity.getDate());
    }

    @Override
    public Orden findByOrdenId(long ordenId) throws Exception {
        Orden orden = ordenAdapter(ordenRepository.findByOrdenId(ordenId));
        if(orden == null){throw new BusinessException("No existe una orden con ese ID");}
        return orden;
    }

    private OrdenEntity ordenAdapter(Orden orden){
        OrdenEntity ordenEntity = new OrdenEntity();
        ordenEntity.setOrdenId(orden.getOrdenId());
        ordenEntity.setPet(petAdapter(orden.getPet()));
        ordenEntity.setPerson(personAdapter(orden.getOwner()));
        ordenEntity.setUser(userAdapter(orden.getVeterinarian()));
        ordenEntity.setMedicationName(orden.getMedicationName());
        ordenEntity.setMedicationDosis(orden.getMedicationDosis());
        ordenEntity.setDate(orden.getDate());
        ordenEntity.setOrdenStatus(orden.getOrdenStatus());
        return ordenEntity;
    }

    private Orden ordenAdapter(OrdenEntity ordenEntity){
        Orden orden = new Orden();
        orden.setOrdenId(ordenEntity.getOrdenId());
        orden.setPet(petAdapter(ordenEntity.getPet()));
        orden.setOwner(personAdapter(ordenEntity.getPerson()));
        orden.setVeterinarian(userAdapter(ordenEntity.getUser()));
        orden.setMedicationName(ordenEntity.getMedicationName());
        orden.setMedicationDosis(ordenEntity.getMedicationDosis());
        orden.setDate(ordenEntity.getDate());
        orden.setOrdenStatus(ordenEntity.getOrdenStatus());
        return orden;
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

    private Pet petAdapter(PetEntity petEntity) {
        Pet pet = new Pet();
        pet.setPetId(petEntity.getPetId());
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

        private User userAdapter(UserEntity userEntity){
        User user = new User();
        user.setPersonDocument(userEntity.getPerson().getDocument());
        user.setPersonName(userEntity.getPerson().getName());
        user.setPersonAge(userEntity.getPerson().getAge());
        user.setUserId(userEntity.getUserId());
        user.setUserName(userEntity.getUserName());
        user.setPassword(userEntity.getPassword());
        user.setRole(userEntity.getRole());
        return user;
    }

    private UserEntity userAdapter(User user){
        //reemplazo metodo personAdpter por mala implementación
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(user.getPersonName());
        personEntity.setDocument(user.getPersonDocument());
        personEntity.setAge(user.getPersonAge());

        UserEntity userEntity = new UserEntity();
        userEntity.setPerson(personEntity);
        userEntity.setUserId(user.getUserId());
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        return userEntity;
    }

    @Override
    public List<Orden> getAllOrden() throws Exception {
        List<OrdenEntity> ordenEntityList = ordenRepository.findAll();
        if(ordenEntityList.isEmpty()){throw new Exception("No hay ordenes en la base de datos");}
        return ordenAdapterList(ordenEntityList);
    }

    public List<Orden> ordenAdapterList(List<OrdenEntity> ordenEntityList){
        List<Orden> ordenList = new ArrayList<>();
        for(OrdenEntity ordenEntity : ordenEntityList){
            ordenList.add(ordenAdapter(ordenEntity));
        }
        return ordenList;
    }

}
