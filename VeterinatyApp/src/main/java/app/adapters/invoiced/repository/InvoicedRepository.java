package app.adapters.invoiced.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.invoiced.entity.InvoicedEntity;

public interface InvoicedRepository extends JpaRepository<InvoicedEntity, Long>{
    public List<InvoicedEntity> findByPersonDocument(long personDocument);
    public List<InvoicedEntity> findByPetPetId(long petId);
    public List<InvoicedEntity> findByOrdenOrdenId(long ordenId);
} 