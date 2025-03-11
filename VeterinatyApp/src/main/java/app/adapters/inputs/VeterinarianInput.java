package app.adapters.inputs;

import org.springframework.beans.factory.annotation.Autowired;

import app.adapters.inputs.utils.PersonValidator;
import app.adapters.inputs.utils.Utils;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.services.VeterinaryServices;
import ch.qos.logback.classic.pattern.Util;

public class VeterinarianInput {
    @Autowired
    private PersonValidator personValidator;
    @Autowired
    private VeterinaryServices veterinaryServices;

    private final String  MENU = "\n 1.Crear Dueño" + "\n 2.Crear mascota" + "\3.Generar Orden" +  "\n4.Generar Historia Clínica" + "\n5.Ver ordenes" + 
                                "\n6.Ver Historia Clinica" + "\n7.Anular Orden" + "\n8.Cerrar Sesión";
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
                    this.createPet();
                    return true;
                case "3":
                    this.createOrden();
                    return true;
                case "8":
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

    public void createCliend() throws Exception{
        System.out.print("Nombre de la persona: ");
        String name = personValidator.nameValidator(Utils.getReader().nextLine());
        System.out.print("Cedula de la persona: ");
        long document = personValidator.documentValudator(Utils.getReader().nextLine());
        System.out.println("Edad de la persona: ");
        int age = personValidator.ageValidator(Utils.getReader().nextLine());
        Person person = new Person();
        person.setPersonName(name);
        person.setPersonDocument(document);
        person.setPersonAge(age);
        veterinaryServices.registerClient(person);
    }

    public void createPet()throws Exception{
        System.out.print("Cedula del dueño: ");
        long personDocument = personValidator.documentValudator(Utils.getReader().nextLine());
        System.out.print("Nombre de la mascota: ");
        String name = personValidator.nameValidator(Utils.getReader().nextLine());
        System.out.print("Edad de la mascota: ");
        int age = personValidator.ageValidator(Utils.getReader().nextLine());
        System.out.print("Especie de la mascota: ");
        String species = personValidator.nameValidator(Utils.getReader().nextLine());
        System.out.print("Raza de la mascota: ");
        String race = personValidator.nameValidator(Utils.getReader().nextLine());
        System.out.print("Peso de la mascota: ");
        double weigth = personValidator.numValidator(Utils.getReader().nextLine());
        Pet pet = new Pet();
        Person owner = new Person();
        owner.setPersonDocument(personDocument);
        pet.setPersonId(owner);
        pet.setPetName(name);
        pet.setPetAge(age);
        pet.setPetSpecies(species);
        pet.setPetRace(race);
        pet.setPetWeight(weigth);
        veterinaryServices.registerPet(pet);
    }
    public void createOrden() throws Exception{
        //AGREGAR LOGICA
    }
}

