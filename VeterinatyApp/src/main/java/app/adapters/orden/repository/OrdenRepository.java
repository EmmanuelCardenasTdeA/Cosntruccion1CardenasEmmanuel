package app.adapters.orden.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.orden.entity.OrdenEntity;

public interface OrdenRepository extends JpaRepository<OrdenEntity, Long>{
 List<OrdenEntity> findByPetId(long petId);
 List<OrdenEntity> findByPersonDocument(long personDocument);
 List<OrdenEntity> findByVetId(long vetDocument);
}
