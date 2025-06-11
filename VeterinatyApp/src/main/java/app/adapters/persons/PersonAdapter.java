package app.adapters.persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.Exceptions.BusinessException;
import app.adapters.persons.entity.PersonEntity;
import app.adapters.persons.repository.PersonRepository;
import app.domain.models.Person;
import app.ports.PersonPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Service
public class PersonAdapter implements PersonPort {
    @Autowired
    private PersonRepository personRepository;

    public boolean existsPerson(long personDocument){
        return personRepository.existsByDocument(personDocument);
    }

    public void savePerson(Person person){
        PersonEntity personEntity = personAdapter(person);        
        personRepository.save(personEntity);
    }
    
    public Person findByPersonDocument(long personDocument)throws Exception{
        PersonEntity personEntity = personRepository.findByDocument(personDocument);
        if(personEntity == null){
            throw new BusinessException("No existe el cliente con el documento: " + personDocument);
        }
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
        personEntity.setName(person.getPersonName());
        personEntity.setDocument(person.getPersonDocument());
        personEntity.setAge(person.getPersonAge());
        return personEntity;
    }
    
}
