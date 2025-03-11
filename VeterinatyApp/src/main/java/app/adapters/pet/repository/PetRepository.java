package app.adapters.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;


public interface PetRepository extends JpaRepository<PetEntity, Long>{
    public void savePet(PetEntity petEntity);
    public PetEntity findByOwnerDocument(long personDocument);
    public PetEntity findByPetId(long petId);
}

