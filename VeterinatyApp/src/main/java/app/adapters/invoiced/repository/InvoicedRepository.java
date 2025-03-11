package app.adapters.invoiced.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.invoiced.entity.InvoicedEntity;

public interface InvoicedRepository extends JpaRepository<InvoicedEntity, Long>{
    public List<InvoicedEntity> findByPersonDocument(long personDocument);
    public List<InvoicedEntity> findByPetId(long petId);
    public List<InvoicedEntity> findByOrdenId(long ordenId);
    public List<InvoicedEntity> findByDate(Date date);
    public void saveInvoiced(InvoicedEntity invoicedEntity);
} 