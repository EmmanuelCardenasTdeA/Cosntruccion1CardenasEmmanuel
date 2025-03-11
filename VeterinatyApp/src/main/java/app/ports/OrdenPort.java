package app.ports;

import java.util.List;

import app.domain.models.Orden;

public interface OrdenPort {
    public void saveOrden(Orden orden);
    public List<Orden> findByPetId(long petId);
    public List<Orden> findByPersonDocument(long personDocument);
    public List<Orden> findByVetId(long vetDocument);
} 
