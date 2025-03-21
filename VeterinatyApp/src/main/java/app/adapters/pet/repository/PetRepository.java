package app.adapters.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.adapters.pet.entity.PetEntity;


@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long>{

    public PetEntity findByOwnerDocument(long personDocument);
    public PetEntity findByPetId(long petId);
}

