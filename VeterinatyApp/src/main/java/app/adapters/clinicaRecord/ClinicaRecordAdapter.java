package app.adapters.clinicaRecord;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.Exceptions.BusinessException;
import app.adapters.clinicaRecord.entity.ClinicaRecordEntity;
import app.adapters.clinicaRecord.repository.ClinicaRecordRepository;
import app.adapters.orden.entity.OrdenEntity;
import app.adapters.persons.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.adapters.users.entity.UserEntity;
import app.domain.models.ClinicaRecord;
import app.domain.models.Orden;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.ClinicalRecordPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class ClinicaRecordAdapter implements ClinicalRecordPort{
    @Autowired
    ClinicaRecordRepository clinicaRecordRepository;

    
    @Override
    public void saveClinicaRecord(ClinicaRecord clinicaRecord) throws Exception {
        ClinicaRecordEntity clinicaRecordEntity = clinicaAdapter(clinicaRecord);
        clinicaRecordRepository.save(clinicaRecordEntity);
        clinicaRecord.setClinicaId(clinicaRecordEntity.getClinicaId());
        clinicaRecord.setDate(clinicaRecordEntity.getDate());
    }  
    
    @Override
    public ClinicaRecord getClinicaRecordByClnicaId(long clinicaId) throws Exception {
        ClinicaRecord clinicaRecord = clinicaAdapter(clinicaRecordRepository.findByClinicaId(clinicaId));
        if(clinicaRecord == null){throw new BusinessException("No existe una historia con ese documento");}
        return clinicaRecord;
    }

    private ClinicaRecordEntity clinicaAdapter(ClinicaRecord clinicaRecord){
        ClinicaRecordEntity clinicaRecordEntity = new ClinicaRecordEntity();
        clinicaRecordEntity.setClinicaId(clinicaRecord.getClinicaId());
        clinicaRecordEntity.setDate(clinicaRecord.getDate());
        clinicaRecordEntity.setConsultation(clinicaRecord.getConsultation());
        clinicaRecordEntity.setSyntomatology(clinicaRecord.getSyntomatology());
        clinicaRecordEntity.setDiagnostic(clinicaRecord.getDiagnostic());
        clinicaRecordEntity.setTreatment(clinicaRecord.getTreatment());
        clinicaRecordEntity.setOrden(ordenAdapter(clinicaRecord.getOrden()));  //Obtener Cliente, Mascota y Veterinario
        clinicaRecordEntity.setVacumHistory(clinicaRecord.getVacumHistory());
        clinicaRecordEntity.setAllergyMedicines(clinicaRecord.getAllergyMedicines());
        clinicaRecordEntity.setDetailsTreatement(clinicaRecord.getDetailsTreatement());
        return clinicaRecordEntity;
    }

    private ClinicaRecord clinicaAdapter(ClinicaRecordEntity clinicaRecordEntity){
        ClinicaRecord clinicalRecord = new ClinicaRecord();
        clinicalRecord.setClinicaId(clinicaRecordEntity.getClinicaId());
        clinicalRecord.setDate(clinicaRecordEntity.getDate());
        clinicalRecord.setConsultation(clinicaRecordEntity.getConsultation());
        clinicalRecord.setSyntomatology(clinicaRecordEntity.getSyntomatology());
        clinicalRecord.setDiagnostic(clinicaRecordEntity.getDiagnostic());
        clinicalRecord.setTreatment(clinicaRecordEntity.getTreatment());
        clinicalRecord.setOrden(ordenAdapter(clinicaRecordEntity.getOrden()));  //Obtener Cliente, Mascota y Veterinario
        clinicalRecord.setVacumHistory(clinicaRecordEntity.getVacumHistory());
        clinicalRecord.setAllergyMedicines(clinicaRecordEntity.getAllergyMedicines());
        clinicalRecord.setDetailsTreatement(clinicaRecordEntity.getDetailsTreatement());
        return clinicalRecord;
    }

        private OrdenEntity ordenAdapter(Orden orden){
        OrdenEntity ordenEntity = new OrdenEntity();
        ordenEntity.setOrdenId(orden.getOrdenId());
        ordenEntity.setPet(petAdapter(orden.getPet()));
        ordenEntity.setPerson(personAdapter(orden.getOwner()));
        ordenEntity.setUser(userAdapter(orden.getVeterinarian()));
        ordenEntity.setMedicationName(orden.getMedicationName());
        ordenEntity.setMedicationDosis(orden.getMedicationDosis());
        ordenEntity.setDate(orden.getDate());
        ordenEntity.setOrdenStatus(orden.getOrdenStatus());
        return ordenEntity;
    }

    private Orden ordenAdapter(OrdenEntity ordenEntity){
        Orden orden = new Orden();
        orden.setOrdenId(ordenEntity.getOrdenId());
        orden.setPet(petAdapter(ordenEntity.getPet()));
        orden.setOwner(personAdapter(ordenEntity.getPerson()));
        orden.setVeterinarian(userAdapter(ordenEntity.getUser()));
        orden.setMedicationName(ordenEntity.getMedicationName());
        orden.setMedicationDosis(ordenEntity.getMedicationDosis());
        orden.setDate(ordenEntity.getDate());
        orden.setOrdenStatus(ordenEntity.getOrdenStatus());
        return orden;
    }
        private PetEntity petAdapter(Pet pet){
        PetEntity petEntity = new PetEntity();
        petEntity.setPetId(pet.getPetId());
        petEntity.setPetName(pet.getPetName());
        petEntity.setPetAge(pet.getPetAge());
        petEntity.setPetSpecies(pet.getPetSpecies());
        petEntity.setPetRace(pet.getPetRace());
        petEntity.setPetWeight(pet.getPetWeight());
        return petEntity;
    }

    private PersonEntity personAdapter(Person person){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(person.getPersonName());
        personEntity.setDocument(person.getPersonDocument());
        person.setPersonAge(person.getPersonAge());
        return personEntity;
    }

    private Pet petAdapter(PetEntity petEntity) {
        Pet pet = new Pet();
        pet.setPetId(petEntity.getPetId());
        pet.setPetName(petEntity.getPetName());
        pet.setPetAge(petEntity.getPetAge());
        pet.setPetSpecies(petEntity.getPetSpecies());
        pet.setPetRace(petEntity.getPetRace());
        pet.setPetWeight(petEntity.getPetWeight());
        return pet;
    }

    private Person personAdapter(PersonEntity personEntity){
        Person person = new Person();
        person.setPersonName(personEntity.getName());
        person.setPersonDocument(personEntity.getDocument());
        person.setPersonAge(personEntity.getAge());
        return person;
    }

        private User userAdapter(UserEntity userEntity){
        User user = new User();
        user.setPersonDocument(userEntity.getPerson().getDocument());
        user.setPersonName(userEntity.getPerson().getName());
        user.setPersonAge(userEntity.getPerson().getAge());
        user.setUserId(userEntity.getUserId());
        user.setUserName(userEntity.getUserName());
        user.setPassword(userEntity.getPassword());
        return user;
    }

    private UserEntity userAdapter(User user){
        //reemplazo metodo personAdpter por mala implementación
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(user.getPersonName());
        personEntity.setDocument(user.getPersonDocument());
        personEntity.setAge(user.getPersonAge());

        UserEntity userEntity = new UserEntity();
        userEntity.setPerson(personEntity);
        userEntity.setUserId(user.getUserId());
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }

    @Override
    public List<ClinicaRecord> getAllClinicaRecord() throws Exception {
        List<ClinicaRecordEntity> clinicaRecordEntityList = clinicaRecordRepository.findAll();
        if(clinicaRecordEntityList.isEmpty()){throw new Exception("No hay historias clinicas");}
        return clinicaAdapterList(clinicaRecordEntityList);
    }
    
    public List<ClinicaRecord> clinicaAdapterList(List<ClinicaRecordEntity> clinicaRecordEntityList){
        List<ClinicaRecord> clinicaRecordList = new ArrayList<>();
        for(ClinicaRecordEntity clinicaRecordEntity : clinicaRecordEntityList){
            clinicaRecordList.add(clinicaAdapter(clinicaRecordEntity));
        }
        return clinicaRecordList;
    }
}
