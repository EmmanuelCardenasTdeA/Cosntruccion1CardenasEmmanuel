package app.ports;
import app.domain.models.Pet;

public interface PetPort {
    public void savePet(Pet pet);
    public Pet findByOwnerDocument(Long personDocument);
    public Pet findByPetId(Long petId);
}
