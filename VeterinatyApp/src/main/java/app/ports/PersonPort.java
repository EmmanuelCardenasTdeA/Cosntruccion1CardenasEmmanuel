package app.ports;

import app.domain.models.Person;

public interface PersonPort {
    public boolean existsPerson(long personDocument)throws Exception;
    public void savePerson(Person person)throws Exception;
    public Person findByPersonDocument(long personDocument)throws Exception;
}
