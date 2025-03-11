package app.ports;
import app.domain.models.Person;
import app.domain.models.Pet;

public interface PetPort {
    public void savePet(Pet pet);
    public Pet findByOwnerDocument(Person person);
}
