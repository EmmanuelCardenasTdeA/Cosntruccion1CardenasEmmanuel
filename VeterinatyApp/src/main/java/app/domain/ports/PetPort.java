package app.domain.ports;

import app.domain.models.Pet;

public interface PetPort {

    public boolean existsPerson(long personDocument);
    public void savePet(Pet pet);
}
