package app.adapters.clinicaRecord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.adapters.clinicaRecord.entity.ClinicaRecordEntity;

@Repository
public interface ClinicaRecordRepository extends JpaRepository<ClinicaRecordEntity, Long>{
    public ClinicaRecordEntity findByOrdenOrdenId(long ordenId);
    public ClinicaRecordEntity findByOrdenPetPetId(long petId);
    public ClinicaRecordEntity findByOrdenPersonDocument(long personDocument);
    public ClinicaRecordEntity findByClinicaId(long clinicaId);
}
