package app.ports;

import java.util.List;

import app.adapters.invoiced.entity.InvoicedEntity;
import app.domain.models.Invoiced;

public interface InvoicedPort {
    public List<Invoiced> getAllInvoices();
    public void saveInvoiced(Invoiced invoiced);
    public List<InvoicedEntity> findByPersonDocument(long personDocument);
    public List<InvoicedEntity> findByPetId(long petId);
    public List<InvoicedEntity> findByOrdenId(long ordenId);

}
