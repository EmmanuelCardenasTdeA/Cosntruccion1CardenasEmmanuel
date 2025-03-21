package app.adapters.inputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.inputs.utils.ClinicaValidator;
import app.adapters.inputs.utils.OrdenValidator;
import app.adapters.inputs.utils.PersonValidator;
import app.adapters.inputs.utils.PetValidator;
import app.adapters.inputs.utils.UserValidator;
import app.adapters.inputs.utils.Utils;
import app.domain.models.ClinicaRecord;
import app.domain.models.Orden;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.domain.services.VeterinaryServices;
import app.ports.ClinicalRecordPort;
import app.ports.InputPort;
import app.ports.OrdenPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Component
public class VeterinarianInput implements InputPort {
    @Autowired
    private PersonValidator personValidator;
    @Autowired
    private VeterinaryServices veterinaryServices;
    @Autowired
    private PetValidator petValidator;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private OrdenValidator ordenValidator;
    @Autowired
    private ClinicaValidator clinicaValidator;
    @Autowired
    private ClinicalRecordPort clinicalRecordPort;
    @Autowired
    private OrdenPort ordenPort;

    private final String  MENU = "\n 1.Crear Dueño" + "\n 2.Crear mascota\3" + "\n 3.Generar Orden" +  "\n 4.Generar Historia Clínica" + "\n 5.Ver ordenes"  +"\n 6. Obetener todas las ordenes"+ 
                                "\n 7.Ver Historia Clinica" +"\n 8.Ver todas las historias clinicas"+ "\n 9.Editar Historia Clínica" +"\n 10.Anular Orden" + "\n 11.Cerrar Sesión";
    public void menu() throws Exception{
        boolean sesion = true;
        while(sesion){
            sesion = options();    
        }
    }
    private boolean options(){
        try {
            System.out.println(MENU);
            String option = Utils.getReader().nextLine();
            switch (option) {
                case "1":
                    this.createCliend();
                    return true;
                case "2":
                    this.cretedPet();
                    return true;
                case "3":
                    this.createOrden();
                    return true;
                case "4":
                    this.createClinica();
                    return true;
                case "5":
                    this.getOrden();
                    return true;
                case "6":
                    veterinaryServices.getAllOrdenes().forEach(orden -> System.out.println(toStringOrden(orden)));
                    System.out.println();
                    return true;
                case "7":
                    this.getClinicalRecord();
                    return true;
                case "8":
                    veterinaryServices.getAllClinicaRecords().forEach(clinicaRecord -> System.out.println(toStringClinica(clinicaRecord)));
                    System.out.println();
                    return true;
                case "9":
                    this.updateClinica();
                    return true;
                case "10":
                    this.cancelOrden();
                    return true;
                case "11":
                    System.out.println("Secion Cerrada");
                    return false;

                default:
                    System.out.println("Opción inválida");
                    return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

     private void createCliend() throws Exception{
        System.out.print("Nombre de la persona: ");
        String name = personValidator.nameValidator(Utils.getReader().nextLine());
        System.out.print("Cedula de la persona: ");
        long document = personValidator.documentValudator(Utils.getReader().nextLine());
        System.out.print("Edad de la persona: ");
        int age = personValidator.ageValidator(Utils.getReader().nextLine());
        Person person = new Person();
        person.setPersonName(name);
        person.setPersonDocument(document);
        person.setPersonAge(age);
        veterinaryServices.registerClient(person);
        System.out.println("Cliente Registrado");
        System.out.println(toStringPerson(person));
    }

    private void cretedPet() throws Exception{
        System.out.print("Cedula del dueño: ");
        long personDocument = personValidator.documentValudator(Utils.getReader().nextLine());
        System.out.print("Nombre de la mascota: ");
        String name = petValidator.nameValidator(Utils.getReader().nextLine());
        System.out.print("Edad de la mascota: ");
        int age = petValidator.ageValidator(Utils.getReader().nextLine());
        System.out.print("Especie de la mascota: ");
        String species = validPet();
        System.out.print("Raza de la mascota: ");
        String race = petValidator.raceValidator(Utils.getReader().nextLine());
        System.out.print("Peso de la mascota(Kg): ");
        double weigth = petValidator.weigthValidator(Utils.getReader().nextLine());
        Pet pet = new Pet();
        Person owner = new Person();
        owner.setPersonDocument(personDocument);
        pet.setPersonId(owner);
        pet.setPetAge(age);
        pet.setPetName(name);
        pet.setPetSpecies(species);
        pet.setPetRace(race);
        pet.setPetWeight(weigth);
        veterinaryServices.registerPet(pet);
        System.out.println("Madcota Registrada");
        System.out.println(toStringPet(pet));
    }

    private void createOrden() throws Exception{
        System.out.print("Ingrese el id de la mascota: ");
        long petId = petValidator.idValidator(Utils.getReader().nextLine());
        System.out.print("Cedula del dueño: ");
        long personDocument = personValidator.documentValudator(Utils.getReader().nextLine());
        System.out.print("Documento del veterinario: ");
        long userDocument = userValidator.documentValudator(Utils.getReader().nextLine());
        System.out.print("Nombre del medicamento: ");
        String medicationName = ordenValidator.nameValidator(Utils.getReader().nextLine());
        System.out.print("Dosis del medicamento: ");
        double medicationDosis = ordenValidator.dosisValidator(Utils.getReader().nextLine());
        Pet pet = new Pet();
        Person person = new Person();
        User user = new User();
        pet.setPetId(petId);
        person.setPersonDocument(personDocument);
        user.setPersonDocument(userDocument);
        Orden orden = new Orden();
        orden.setMedicationName(medicationName);
        orden.setMedicationDosis(medicationDosis);
        orden.setOrdenStatus("Activa");
        orden.setPet(pet);
        orden.setOwner(person);
        orden.setVeterinarian(user);
        System.out.println(orden.getPet().getPetId());
        veterinaryServices.registerOrden(orden);
    }

    private void createClinica()throws Exception{
        System.out.print("Motivo de consulta: ");
        String consultation = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
        System.out.print("Diagnostico: ");
        String diagnostic = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
        System.out.print("Sintomatología: ");
        String syntomatology = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
        System.out.print("Tratamiento: ");
        String treatment = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
        System.out.print("Id de la Orden: ");
        long ordenId = ordenValidator.ordenIdValidator(Utils.getReader().nextLine());
        Orden orden = new Orden();
        orden.setOrdenId(ordenId);
        orden = veterinaryServices.getOrdenByOrdenId(ordenId);
        String vacumHistory = validVacumHistory(orden.getPet());
        System.out.print("Allergia a medicamentos: ");
        String allergyMedicines = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
        System.out.print("Detalles del tratamiento: ");
        String treatmentDetails = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
        
        ClinicaRecord clinicaRecord = new ClinicaRecord();
        clinicaRecord.setConsultation(consultation);
        clinicaRecord.setDiagnostic(diagnostic);
        clinicaRecord.setSyntomatology(syntomatology);
        clinicaRecord.setTreatment(treatment);
        clinicaRecord.setOrden(orden);
        clinicaRecord.setVacumHistory(vacumHistory);
        clinicaRecord.setAllergyMedicines(allergyMedicines);
        clinicaRecord.setDetailsTreatement(treatmentDetails);
        veterinaryServices.registerClinicaRecord(clinicaRecord);
    }

    private void getOrden()throws Exception{
        try {
            Orden orden = new Orden();
            System.out.print("Ingrese el id de la orden:");
            long ordenId = ordenValidator.ordenIdValidator(Utils.getReader().nextLine());
            orden = veterinaryServices.getOrdenByOrdenId(ordenId);
            System.out.println(toStringOrden(orden));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
    }

    private void getClinicalRecord() throws Exception{
        try {
            ClinicaRecord clinicaRecord = new ClinicaRecord();
            System.out.print("Ingrese el id de la Historia Clinica: ");
            long clinicalRecordId = clinicaValidator.clinicaIdValidator(Utils.getReader().nextLine());
            clinicaRecord = veterinaryServices.getClinicalRecordByClinicaId(clinicalRecordId);
            System.out.println(toStringClinica(clinicaRecord));
            }
         catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateClinica()throws Exception{
        try {
            System.out.println("1. Editar Historia Clinica(Motivo de consulta, Sintomatología, Etc...) 2. Editar Orden(Medicamentos dosis)");
            String option = Utils.getReader().nextLine();
            switch (option) {
                case "1":
                    System.out.print("Ingrese el id de la Historia Clinica: ");
                    long clinicaId = clinicaValidator.clinicaIdValidator(Utils.getReader().nextLine());
                    ClinicaRecord clinicaRecord = clinicalRecordPort.getClinicaRecordByClnicaId(clinicaId);
                    System.out.print("Motivo de consulta: ");
                    String consultation = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
                    System.out.print("Sintomatología: ");
                    String syntomatology = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
                    System.out.print("Diagnostico: ");
                    String diagnostic = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
                    System.out.print("Tratamiento: ");
                    String treatment = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
                    System.out.print("Orden Id anterior: " + clinicaRecord.getOrden().getOrdenId() + " Orden Id: ");
                    long ordenIdr = ordenValidator.ordenIdValidator(Utils.getReader().nextLine());
                    Orden ordenr = veterinaryServices.getOrdenByOrdenId(ordenIdr);
                    String vacumHistory = validVacumHistory(ordenr.getPet());
                    System.out.print("Allergia a medicamentos: ");
                    String allergyMedicines = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
                    System.out.print("Detalles del tratamiento: ");
                    String treatmentDetails = clinicaValidator.stringClinicaValidator(Utils.getReader().nextLine());
                    clinicaRecord.setConsultation(consultation);
                    clinicaRecord.setSyntomatology(syntomatology);
                    clinicaRecord.setDiagnostic(diagnostic);
                    clinicaRecord.setTreatment(treatment);
                    clinicaRecord.setOrden(ordenr);
                    clinicaRecord.setVacumHistory(vacumHistory);
                    clinicaRecord.setAllergyMedicines(allergyMedicines);
                    clinicaRecord.setDetailsTreatement(treatmentDetails);
                    veterinaryServices.updateClinicaRecord(clinicaRecord);
                    break;
                case "2":
                    System.out.println("Ingresar id de la orden: ");
                    long ordenId = ordenValidator.ordenIdValidator(Utils.getReader().nextLine());
                    Orden orden = veterinaryServices.getOrdenByOrdenId(ordenId);
                    System.out.println(toStringOrden(orden));
                    System.out.print("Nombre del medicamento: ");
                    String medicationName = ordenValidator.nameValidator(Utils.getReader().nextLine());
                    System.out.print("Dosis del medicamento: ");
                    double medicationDosis = ordenValidator.dosisValidator(Utils.getReader().nextLine());
                    orden.setMedicationName(medicationName);
                    orden.setMedicationDosis(medicationDosis);
                    veterinaryServices.updateOrden(orden);
                    break;
                default:
                    System.out.println("Opción Invalida");
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void cancelOrden() throws Exception{
        System.out.print("Ingrese el id de la orden: ");
        long ordenId = ordenValidator.ordenIdValidator(Utils.getReader().nextLine());
        Orden orden = veterinaryServices.getOrdenByOrdenId(ordenId);
        System.out.println(toStringOrden(orden));
        veterinaryServices.cancelOrden(orden);
        System.out.println("Orden Anulada");
        System.out.println(toStringOrden(veterinaryServices.getOrdenByOrdenId(ordenId)));
    }

    private String toStringOrden(Orden orden){
        return "\nID Orden: "+orden.getOrdenId()+"\nId Mascota: "+orden.getPet().getPetId()+"\nCedula Dueño: "+orden.getOwner().getPersonDocument()
                                    +"\nCedula Veterinario: "+orden.getVeterinarian().getPersonDocument()+"\nNombre medigamento: "+orden.getMedicationName()
                                    +"\nDosis: "+orden.getMedicationDosis()+"\nFecha: "+orden.getDate()+"\nEstado: "+orden.getOrdenStatus()+"\n";
    }

    private String toStringClinica(ClinicaRecord clinicaRecord){
        return "\nID Historia Clinica: "+clinicaRecord.getClinicaId()+"\nFecha: "+clinicaRecord.getDate()+"\nMotivo de consulta: "+clinicaRecord.getConsultation()
                                    +"\nDiagnostico: "+clinicaRecord.getDiagnostic()+"\nSintomatología: "+clinicaRecord.getSyntomatology()+"\nTratamiento: "+clinicaRecord.getTreatment()
                                    +"\nId Orden: "+clinicaRecord.getOrden().getOrdenId()+"\nId Mascota: "+clinicaRecord.getOrden().getPet().getPetId()+"\nCedula Dueño: "+clinicaRecord.getOrden().getOwner().getPersonDocument()
                                    +"\nCedula Veterinario: "+clinicaRecord.getOrden().getVeterinarian().getPersonDocument()+"\nNombre medicamento: "+clinicaRecord.getOrden().getMedicationName()+"\nDosis: "+clinicaRecord.getOrden().getMedicationDosis()
                                    +"\nHistorial de vacunas: "+clinicaRecord.getVacumHistory()+"\nAlergia a medicamentos: "+clinicaRecord.getAllergyMedicines()
                                    +"\nDetalles del tratamiento: "+clinicaRecord.getDetailsTreatement()+"\n";
    }

    private String toStringPet(Pet pet){
        return "\nDocumento del dueño: "+pet.getPersonId().getPersonDocument()+"\nNombre del dueño: "+pet.getPersonId().getPersonName()+"\nID Mascota: "+pet.getPetId()+"\nNombre: "+pet.getPetName()+"\nEdad: "+pet.getPetAge()+"\nEspecie: "+pet.getPetSpecies()+"\nRaza: "+pet.getPetRace()+"\nPeso(Kg): "+pet.getPetWeight();
    }
    private String toStringPerson(Person person){
        return "\nNombre: "+person.getPersonName()+"\nDocumento: "+person.getPersonDocument()+"\nEdad: "+person.getPersonAge();
    }

    private String validPet() throws Exception {
        String option = "";
        boolean validOption = false;
    
        while (!validOption) {
            try {
                System.out.println("1. Perro 2. Gato 3. Otro");
                option = Utils.getReader().nextLine();
                
                switch (option) {
                    case "1":
                        validOption = true;
                        return "Perro";
                    case "2":
                        validOption = true;
                        return "Gato";
                    case "3":
                        validOption = true;
                        System.out.println("Ingrese la especie: ");
                        String otherPet = Utils.getReader().nextLine();
                        return otherPet;
                    default:
                        System.out.println("Opción inválida. Por favor, ingrese 1 para Perro, 2 para Gato, o 3 para Otro.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
                return e.getMessage();
            }
        }
    
        return "";
    }

    private String validVacumHistory(Pet pet) throws Exception{
        if(pet.getPetSpecies().equalsIgnoreCase("Perro") || pet.getPetSpecies().equalsIgnoreCase("Gato")){
            System.out.print("Historial de vacunas: ");
            String vacum = Utils.getReader().nextLine();
            return vacum;
        }
        return "No aplica";
    }
}

