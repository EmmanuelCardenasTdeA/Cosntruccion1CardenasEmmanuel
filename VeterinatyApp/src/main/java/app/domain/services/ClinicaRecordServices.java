package app.domain.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import app.domain.models.ClinicaRecord;


@Service
public class ClinicaRecordServices {
    
    private List<Map<String, Object>> listClinicaRecords = new ArrayList<Map<String, Object>>();

    public void saveClinicalRecord(ClinicaRecord clinicaRecord){ 
        Map<String, Object>  mapClinica = new HashMap<String,Object>();
        mapClinica.put("date", clinicaRecord.getDate());
        mapClinica.put("veterinary", clinicaRecord.getVeterinary());
        mapClinica.put("cosultation", clinicaRecord.getCosultation());
        mapClinica.put("syntomatology", clinicaRecord.getSyntomatology());
        mapClinica.put("diagnostic", clinicaRecord.getDiagnostic());
        mapClinica.put("treatment", clinicaRecord.getTreatment());
        mapClinica.put("orden", clinicaRecord.getOrden());
        mapClinica.put("vacumHistory", clinicaRecord.getVacumHistory());
        mapClinica.put("allergyMedicines", clinicaRecord.getAllergyMedicines());
        mapClinica.put("detailsTreatement", clinicaRecord.getDetailsTreatement());
        mapClinica.put("isActiva", clinicaRecord.isActiva());
        listClinicaRecords.add(mapClinica);
    }
    
}
