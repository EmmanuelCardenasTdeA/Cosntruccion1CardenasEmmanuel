package app.adapters.invoiced.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.adapters.invoiced.entity.InvoicedEntity;
@Repository
public interface InvoicedRepository extends JpaRepository<InvoicedEntity, Long>{
    public InvoicedEntity findByOrdenOrdenId(long ordenId);
    public InvoicedEntity findByOrdenPetPetId(long petId);
    public InvoicedEntity findByOrdenPersonDocument(long personDocument);
    public InvoicedEntity findByOrdenUserPersonDocument(long personDocument);
    public InvoicedEntity findByInvoicedId(long invoicedId);
} 