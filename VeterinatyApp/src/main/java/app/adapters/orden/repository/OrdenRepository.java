package app.adapters.orden.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.orden.entity.OrdenEntity;

public interface OrdenRepository extends JpaRepository<OrdenEntity, Long>{
    public List<OrdenEntity> findByPetId(long petId);
    public List<OrdenEntity> findByPersonDocument(long personDocument);
    public List<OrdenEntity> findByVetId(long vetDocument);
    public void saveOrden(OrdenEntity ordenEntity);
}
