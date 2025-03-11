package app.adapters.orden;

import java.util.List;

import app.domain.models.Orden;
import app.ports.OrdenPort;

public class OrdenAdapter implements OrdenPort{

    @Override
    public void saveOrden(Orden orden) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveOrden'");
    }

    @Override
    public List<Orden> findByPetId(long petId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPetId'");
    }

    @Override
    public List<Orden> findByPersonDocument(long personDocument) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPersonDocument'");
    }

    @Override
    public List<Orden> findByVetId(long vetDocument) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByVetId'");
    }


}
