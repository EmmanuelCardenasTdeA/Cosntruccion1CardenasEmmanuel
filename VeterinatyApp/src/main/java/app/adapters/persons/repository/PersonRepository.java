package app.adapters.persons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.adapters.persons.entity.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {


    public boolean existsByDocument(long document);
    
    public PersonEntity findByDocument(long document);

}
