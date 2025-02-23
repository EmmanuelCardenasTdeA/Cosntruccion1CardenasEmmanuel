package app.adapters.persons.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.persons.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    boolean existsByPersonDocument(long personDocument);
    
    PersonEntity findByDocument(long personDocument);


}
