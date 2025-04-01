package app.adapters.orden.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.adapters.orden.entity.OrdenEntity;

@Repository
public interface OrdenRepository extends JpaRepository<OrdenEntity, Long>{
    public OrdenEntity findByPetPetId(long petId);
    public OrdenEntity findByPersonDocument(long personDocument);
    public OrdenEntity findByUserUserId(long userId);
    public OrdenEntity findByOrdenId(long ordenId);
}
