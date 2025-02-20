package app.domain.ports;

import app.domain.models.Person;

public interface PersonPort {
    public boolean existsPerson(long personDocument);
    public void savePerson(Person person);
    public Person findByDocument(long personDocument);
}
