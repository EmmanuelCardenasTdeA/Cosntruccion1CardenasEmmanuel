package app.adapters.persons;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.persons.repository.PersonRepository;
import app.domain.models.Person;
import app.domain.ports.PersonPort;

public class PersonAdapter implements PersonPort {
    private PersonRepository personRepository;

    public boolean existsPerson(long personDocument){
        return personRepository.existsByPersonDocument(personDocument);
    }

    public void savePerson(Person person){
        PersonEntity personEntity = new PersonEntity(person);
        personRepository.save(personEntity);
        person.setPersonDocument(personEntity.getPersonDocument());
    }

    public Person findByDocument(long document){
        PersonEntity personEntity = personRepository.findByDocument(document);
        return adapterPerson(personEntity);
    }

    private Person adapterPerson(PersonEntity personEntity){
        Person person = new Person();
        person.setPersonDocument(personEntity.getPersonDocument());
        person.setPersonName(personEntity.getPersonName());
        person.setPersonAge(personEntity.getPersonAge());
        return person;
    }
}
