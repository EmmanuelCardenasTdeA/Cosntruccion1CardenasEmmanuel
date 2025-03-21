package app.ports;
import java.util.List;

import app.domain.models.ClinicaRecord;

public interface ClinicalRecordPort {
    public ClinicaRecord getClinicaRecordByClnicaId(long clinicaId)throws Exception;
    public List<ClinicaRecord> getAllClinicaRecord()throws Exception;
    public void saveClinicaRecord(ClinicaRecord clinicaRecord)throws Exception;
}
