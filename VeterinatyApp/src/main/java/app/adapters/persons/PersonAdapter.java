package app.adapters.persons;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.persons.repository.PersonRepository;
import app.domain.models.Person;
import app.domain.ports.PersonPort;

public class PersonAdapter implements PersonPort {
    private PersonRepository personRepository;

    public boolean existsPerson(long personDocument){
        return personRepository.existsByDocument(personDocument);
    }

    public void savePerson(Person person){
        PersonEntity personEntity = personAdapter(person);        
        personRepository.save(personEntity);
        person.setPersonDocument(personEntity.getDocument());
    }
    
    public Person findByPersonDocument(long personDocument){
        PersonEntity personEntity = personRepository.findByDocument(personDocument);
        return personAdapter(personEntity);
    }

    
    private Person personAdapter(PersonEntity personEntity){
        Person person = new Person();
        person.setPersonName(personEntity.getName());
        person.setPersonDocument(personEntity.getDocument());
        person.setPersonAge(personEntity.getAge());
        return person;
    }

    private PersonEntity personAdapter(Person person){
        PersonEntity personEntity = new PersonEntity();
        person.setPersonName(personEntity.getName());
        person.setPersonDocument(personEntity.getDocument());
        person.setPersonAge(personEntity.getAge());
        return personEntity;
    }

}
