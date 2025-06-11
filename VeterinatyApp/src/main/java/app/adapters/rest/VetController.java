package app.adapters.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.Exceptions.BusinessException;
import app.Exceptions.InputException;
import app.adapters.rest.request.ClinicalRecordRequest;
import app.adapters.rest.request.OrdenRequest;
import app.adapters.rest.request.PersonRequest;
import app.adapters.rest.request.PetRequest;
import app.adapters.rest.resposive.ClinicalRest;
import app.adapters.rest.resposive.OrderRest;
import app.adapters.rest.resposive.PersonRest;
import app.adapters.rest.resposive.PetRest;
import app.domain.models.ClinicaRecord;
import app.domain.models.Orden;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.domain.services.VeterinaryServices;
import app.ports.ClinicalRecordPort;
import app.ports.OrdenPort;
import app.ports.PersonPort;
import app.ports.PetPort;
import app.ports.UserPort;

@RestController
public class VetController {
    @Autowired
    private VeterinaryServices veterinaryServices;
    @Autowired
    private PersonPort personPort;
    // @Autowired
    // private PersonValidator personValidator;
    @Autowired
    private PetPort petPort;
    @Autowired
    private UserPort userPort;
    @Autowired
    private OrdenPort ordenPort;
    @Autowired
    private ClinicalRecordPort clinicalRecordPort;

    @GetMapping("/")
    public String itsAlive(){
        return "Its alive!";
    }

    @PostMapping("/client")
    public ResponseEntity registerClient(@RequestBody PersonRequest request){ 
        try {
            System.out.println(request.toString());
            Person person = new Person();
            //CONSULTAR CON EL PROFESOR
                //person.setPersonDocument(personValidator.documentValidator(Long.toString(request.getPersonDocument())));
            person.setPersonDocument(request.getPersonDocument());
            person.setPersonName(request.getPersonName());
            person.setPersonAge(request.getPersonAge());

            if(request.getPersonDocument() == 0 || request.getPersonName() == null || request.getPersonAge() == 0){
                throw new InputException("Datos invalidos");
            }

            veterinaryServices.registerClient(person);
            
            return new ResponseEntity("Se ha registrado el cliente", HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(), HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pets")
    public ResponseEntity registerPet(@RequestBody PetRequest request){
        try {
            System.out.println(request.toString());
            System.out.println();
            
            Pet pet = new Pet();
            long ownerDocument = request.getOwnerDocument();
            Person owner = personPort.findByPersonDocument(ownerDocument);
            pet.setPersonId(owner);
            pet.setPetName(request.getPetName());
            pet.setPetAge(request.getPetAge());
            pet.setPetSpecies(request.getPetSpecies());
            pet.setPetRace(request.getPetRace());
            pet.setPetWeight(request.getPetWeight());

            if(request.getOwnerDocument()==0 || request.getPetName() == null || request.getPetSpecies() == null || request.getPetRace() == null || request.getPetWeight() == 0){
                throw new InputException("Datos invalidos");
            }

            veterinaryServices.registerPet(pet);
            return new ResponseEntity("Se ha registrado la mascota", HttpStatus.OK);
            
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(), HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity getPet(@PathVariable long id){
        try {
            Pet pet = petPort.findByPetId(id);
            PetRest petRest = petRestAdapter(pet);
            return new ResponseEntity(petRest, HttpStatus.OK);
        }catch (BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/orden")
    public ResponseEntity registerOrden(@RequestBody OrdenRequest request){
        try {
            long petId = request.getPetId();
            Pet pet = petPort.findByPetId(petId);
            if (pet == null){
                throw new BusinessException("No existe la mascota con el id " + petId);
            }

            Person owner = pet.getPersonId();

            long vetId = request.getVeterinarianId();
            User veterinarian = userPort.fingByUserId(vetId);
            if (veterinarian == null){
                throw new BusinessException("No existe el veterinario con el documento: " + vetId);
            }

            Orden orden = new Orden();
            orden.setPet(pet);
            orden.setOwner(owner);
            orden.setVeterinarian(veterinarian);
            orden.setMedicationName(request.getMedicationName());
            orden.setMedicationDosis(request.getMedicationDosis());
            orden.setOrdenStatus("Activa");
            if(request.getPetId()==0 || request.getVeterinarianId()==0 || request.getMedicationName() == null || request.getMedicationDosis() == 0){
                throw new InputException("Datos invalidos");
            }
            veterinaryServices.registerOrden(orden);
            return new ResponseEntity("Se ha registrado la orden", HttpStatus.OK);
        }catch (BusinessException be) {
            return new ResponseEntity(be.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/Record")
    public ResponseEntity registerRecord(@RequestBody ClinicalRecordRequest request){
        try {
            System.out.println(request.toString());
            long ordenId = request.getOrderId();
            Orden orden = ordenPort.findByOrdenId(ordenId);
            ClinicaRecord clinicaRecord = new ClinicaRecord();
            clinicaRecord.setOrden(orden);
            clinicaRecord.setConsultation(request.getConsultation());
            clinicaRecord.setSyntomatology(request.getSyntomatology());
            clinicaRecord.setDiagnostic(request.getDiagnostic());
            clinicaRecord.setTreatment(request.getTreatment());

            if ( clinicaRecord.getOrden().getPet().getPetSpecies().equalsIgnoreCase("perro") || clinicaRecord.getOrden().getPet().getPetSpecies().equalsIgnoreCase("gato")) {
                clinicaRecord.setVacumHistory(request.getVacumHistory());
            } else {
                clinicaRecord.setVacumHistory("No aplica");
            }
            
            clinicaRecord.setAllergyMedicines(request.getAllergyMedicines());
            clinicaRecord.setDetailsTreatement(request.getDetailsTreatement());

            if(request.getOrderId()==0 || request.getConsultation() == null || request.getSyntomatology() == null || request.getDiagnostic() == null || request.getTreatment() == null){
                throw new InputException("Datos invalidos");
            }

            veterinaryServices.registerClinicaRecord(clinicaRecord);
            return new ResponseEntity("Se ha registado la historia clinica", HttpStatus.OK);

        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/GetAllRecord")    
    public ResponseEntity getAlClinicaRecords(){
        try {
            List<ClinicaRecord> records = veterinaryServices.getAllClinicaRecords();
            List<ClinicalRest> clinicalRestList = clinicaAdapterList(records);
            return new ResponseEntity(clinicalRestList, HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/GetAllOrden")
    public ResponseEntity getAllOrdenes(){
        try {
            List<Orden> ordenes = veterinaryServices.getAllOrdenes();
            List<OrderRest> orderRerstList = ordenAdapterList(ordenes);
            return new ResponseEntity(orderRerstList,HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @GetMapping("/GetOrden/{ordenId}")
    public ResponseEntity getOrden(@PathVariable long ordenId){
        try {
            Orden orden = ordenPort.findByOrdenId(ordenId);
            OrderRest orderRest = orderRestAdapter(orden);
            if(orden == null){
                throw new BusinessException("No existe la orden con el id: " + ordenId);
            }
            return new ResponseEntity(orderRest,HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("GetRecord/{clinicaId}")
    public ResponseEntity getRecord(@PathVariable long clinicaId){
        try {
            ClinicaRecord clinicaRecord = clinicalRecordPort.getClinicaRecordByClnicaId(clinicaId);
            ClinicalRest clinicalRest = clinicalRestAdapter(clinicaRecord);
            if(clinicaRecord == null){
                throw new BusinessException("No existe la historia clinica con el id: " + clinicaId);
            }
            return new ResponseEntity(clinicalRest, HttpStatus.OK);
        }catch (Exception e){
            return null;
        }
    }

    @PatchMapping("/CancelOrden/{ordenId}")
    public ResponseEntity cancelOrden(@PathVariable long ordenId){
        try {
            Orden orden = ordenPort.findByOrdenId(ordenId);
            if(orden == null){
                throw new BusinessException("No existe la orden con el id: " + ordenId);
            }
            veterinaryServices.cancelOrden(orden);
            return new ResponseEntity("Se ha cancelado la orden", HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/UpdateOrden/{ordenId}")
    public ResponseEntity updateOrden(@PathVariable long ordenId, @RequestBody OrdenRequest request){
        try {
            Orden orden = ordenPort.findByOrdenId(ordenId);
            if(orden == null){
                throw new BusinessException("No existe la orden con el id: " + ordenId);
            }
            if(request.getMedicationName() != null) {orden.setMedicationName(request.getMedicationName());}
            if(request.getMedicationDosis() != 0) {orden.setMedicationDosis(request.getMedicationDosis());}
            veterinaryServices.updateOrden(orden);
            return new ResponseEntity("Se ha actualizado la orden", HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/UpdateRecord/{clinicaId}")
    public ResponseEntity updateRecord(@PathVariable long clinicaId, @RequestBody ClinicalRecordRequest request){
        try {
            ClinicaRecord clinicaRecord = clinicalRecordPort.getClinicaRecordByClnicaId(clinicaId);
            if(clinicaRecord == null){
                throw new BusinessException("No existe la historia clinica con el id: " + clinicaId);
            }
            if(request.getConsultation() != null) {clinicaRecord.setConsultation(request.getConsultation());}
            if(request.getSyntomatology() != null) {clinicaRecord.setSyntomatology(request.getSyntomatology());}
            if(request.getDiagnostic() != null) {clinicaRecord.setDiagnostic(request.getDiagnostic());}
            if(request.getTreatment() != null) {clinicaRecord.setTreatment(request.getTreatment());}

            if(clinicaRecord.getOrden().getPet().getPetSpecies().equalsIgnoreCase("perro") || clinicaRecord.getOrden().getPet().getPetSpecies().equalsIgnoreCase("gato")){
                if(request.getVacumHistory() != null) {clinicaRecord.setVacumHistory(request.getVacumHistory());}
            }
            
            if(request.getAllergyMedicines()!= null){clinicaRecord.setAllergyMedicines(request.getAllergyMedicines());}
            if(request.getDetailsTreatement() != null){clinicaRecord.setDetailsTreatement(request.getDetailsTreatement());}
            veterinaryServices.updateClinicaRecord(clinicaRecord);
            return new ResponseEntity("Se ha actualizado la historia clinica", HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private PetRest petRestAdapter(Pet pet){
        PetRest petRest = new PetRest();
        petRest.setPetId(pet.getPetId());
        petRest.setPetName(pet.getPetName());
        petRest.setPetAge(pet.getPetAge());
        petRest.setPetSpecies(pet.getPetSpecies());
        petRest.setPetRace(pet.getPetRace());
        petRest.setPetWeight(pet.getPetWeight());
        return petRest;
    }

    private PersonRest personRestAdapter(Person person){
        PersonRest personRest = new PersonRest();
        personRest.setPersonDocument(person.getPersonDocument());
        personRest.setPersonName(person.getPersonName());
        personRest.setPersonAge(person.getPersonAge());
        return personRest;
    }

    private PersonRest userRestAdapter(User user){
        PersonRest personRest = new PersonRest();
        personRest.setPersonDocument(user.getPersonDocument());
        personRest.setPersonName(user.getPersonName());
        personRest.setPersonAge(user.getPersonAge());
        return personRest;
    }

    private OrderRest orderRestAdapter(Orden orden){
        OrderRest orderRest = new OrderRest();
        orderRest.setOrdenId(orden.getOrdenId());
        orderRest.setPet(petRestAdapter(orden.getPet()));
        orderRest.setOwner(personRestAdapter(orden.getOwner()));
        orderRest.setVeterinarian(userRestAdapter(orden.getVeterinarian()));
        orderRest.setMedicationName(orden.getMedicationName());
        orderRest.setMedicationDosis(orden.getMedicationDosis());
        orderRest.setOrdenStatus(orden.getOrdenStatus());
        orderRest.setDate(orden.getDate());
        return orderRest;
    }

    public List<OrderRest> ordenAdapterList(List<Orden> ordenList){
        List<OrderRest> orderRestList = new ArrayList<>();
        for(Orden orden : ordenList){
            orderRestList.add(orderRestAdapter(orden));
        }
        return orderRestList;
    }

    public ClinicalRest clinicalRestAdapter(ClinicaRecord clinicaRecord){
        ClinicalRest clinicalRest = new ClinicalRest();
        clinicalRest.setClinicaId(clinicaRecord.getClinicaId());
        clinicalRest.setDate(clinicaRecord.getDate());
        clinicalRest.setConsultation(clinicaRecord.getConsultation());
        clinicalRest.setSyntomatology(clinicaRecord.getSyntomatology());
        clinicalRest.setDiagnostic(clinicaRecord.getDiagnostic());
        clinicalRest.setTreatment(clinicaRecord.getTreatment());
        clinicalRest.setOrden(orderRestAdapter(clinicaRecord.getOrden()));
        clinicalRest.setVacumHistory(clinicaRecord.getVacumHistory());
        clinicalRest.setAllergyMedicines(clinicaRecord.getAllergyMedicines());
        clinicalRest.setDetailsTreatement(clinicaRecord.getDetailsTreatement());
        return clinicalRest;
    }

    public List<ClinicalRest> clinicaAdapterList(List<ClinicaRecord> clinicaRecordList){
        List<ClinicalRest> clinicalRestList = new ArrayList<>();
        for(ClinicaRecord clinicaRecord : clinicaRecordList){
            clinicalRestList.add(clinicalRestAdapter(clinicaRecord));
        }
        return clinicalRestList;
    }

}
