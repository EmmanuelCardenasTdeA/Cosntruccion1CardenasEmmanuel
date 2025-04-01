package app.adapters.pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.adapters.persons.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.adapters.pet.repository.PetRepository;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.ports.PetPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
public class PetAdapter implements PetPort{
    @Autowired
    private PetRepository petRepository;

    @Override
    public void savePet(Pet pet) {
        PetEntity petEntity = petAdapter(pet);
        petRepository.save(petEntity);
        pet.setPetId(petEntity.getPetId());
        System.out.println("Mascota Creada.");
    }

    @Override
    public Pet findByOwnerDocument(Long personDocument)throws Exception{
        PetEntity petEntity = petRepository.findByOwnerDocument(personDocument);
        if(petEntity == null){
            throw new Exception("No existe un Cliente con esa cedula");
        }
        return petAdapter(petEntity);
    }


    @Override
    public Pet findByPetId(Long petId)throws Exception {
       PetEntity petEntity = petRepository.findByPetId(petId);
       if(petEntity == null){
        throw new Exception("No existe un Cliente con esa cedula");
    }
       return petAdapter(petEntity);
    }

    private PetEntity petAdapter(Pet pet){
        PetEntity petEntity = new PetEntity();
        petEntity.setOwner(personAdapter(pet.getPersonId()));
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
        personEntity.setAge(person.getPersonAge());
        return personEntity;
    }

    private Person personAdapter(PersonEntity personEntity){
        Person person = new Person();
        person.setPersonName(personEntity.getName());
        person.setPersonDocument(personEntity.getDocument());
        person.setPersonAge(personEntity.getAge());
        return person;
    }

    private Pet petAdapter(PetEntity petEntity){
        Pet pet = new Pet();
        pet.setPersonId(personAdapter(petEntity.getOwner()));
        pet.setPetName(petEntity.getPetName());
        pet.setPetAge(petEntity.getPetAge());
        pet.setPetId(petEntity.getPetId());
        pet.setPetSpecies(petEntity.getPetSpecies());
        pet.setPetRace(petEntity.getPetRace());
        pet.setPetWeight(petEntity.getPetWeight());
        return pet;
    }
}
