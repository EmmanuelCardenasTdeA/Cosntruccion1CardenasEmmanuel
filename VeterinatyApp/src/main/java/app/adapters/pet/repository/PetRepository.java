package app.adapters.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.domain.models.Pet;

public interface PetRepository extends JpaRepository<PetEntity, Long>{
    public void save(Pet pet);
    public PetEntity findByOwnerDocument(PersonEntity personDocument);
}

