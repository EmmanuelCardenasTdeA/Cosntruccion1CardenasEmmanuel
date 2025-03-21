package app.ports;
import app.domain.models.Pet;


public interface PetPort {
    public void savePet(Pet pet)throws Exception;
    public Pet findByOwnerDocument(Long personDocument)throws Exception;
    public Pet findByPetId(Long petId)throws Exception;
}
