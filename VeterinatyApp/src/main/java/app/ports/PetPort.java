package app.ports;
import app.domain.models.Person;
import app.domain.models.Pet;

public interface PetPort {
    public void save(Pet pet);
    public Pet findByOwnerDocument(Person person);
}
