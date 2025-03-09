package app.ports;
import app.domain.models.Person;

public interface PersonPort {
    public boolean existsPerson(long personDocument);
    public void savePerson(Person person);
    public Person findByPersonDocument(long personDocument);
}
