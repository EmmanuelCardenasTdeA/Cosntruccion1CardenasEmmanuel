package app.adapters.orden.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.orden.entity.OrdenEntity;

public interface OrdenRepository extends JpaRepository<OrdenEntity, Long>{
    public List<OrdenEntity> findByPetPetId(long petId);
    public List<OrdenEntity> findByPersonDocument(long personDocument);
    public List<OrdenEntity> findByUserUserId(long userId);
}
