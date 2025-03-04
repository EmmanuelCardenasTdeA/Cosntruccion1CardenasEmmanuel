package app.adapters.pet;

import org.springframework.stereotype.Service;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.persons.repository.PersonRepository;
import app.adapters.pet.entity.PetEntity;
import app.adapters.pet.repository.PetRepository;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.ports.PetPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
public class PetAdapter implements PetPort{
    private PersonRepository personRepository;
    private PetRepository petRepository;


    @Override
    public void save(Pet pet) {
        PetEntity petEntity = petAdapter(pet);
        petRepository.save(petEntity);
        pet.setPetId(petEntity.getPetId());
    }

    @Override
    public Pet findByOwnerDocument(Person person){
        PetEntity petEntity = petRepository.findByOwnerDocument(person);
        return petAdapter(petEntity);
    }

    private PetEntity petAdapter(Pet pet){
        PetEntity petEntity = new PetEntity();
        petEntity.setOwnerName(pet.getOwnerName());
        petEntity.setOwnerDocument(pet.getOwnerDocument());
        return petEntity;
    }

    private Pet petAdapter(PetEntity petEntity){
        Pet pet = new Pet();
        pet.setOwnerName(petEntity.getOwnerName());
        pet.setOwnerDocument(petEntity.getOwnerDocument());
        pet.setPetName(petEntity.getPetName());
        pet.setPetAge(petEntity.getPetAge());
        pet.setPetId(petEntity.getPetId());
        pet.setPetRace(petEntity.getPetRace());
        pet.setPetWeight(petEntity.getPetWeight());
        return pet;
    }
}
