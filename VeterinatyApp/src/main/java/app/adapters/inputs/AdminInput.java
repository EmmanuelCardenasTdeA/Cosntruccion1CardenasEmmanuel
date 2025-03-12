package app.adapters.inputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.inputs.utils.PersonValidator;
import app.adapters.inputs.utils.PetValidator;
import app.adapters.inputs.utils.UserValidator;
import app.adapters.inputs.utils.Utils;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.domain.services.AdminServices;
import app.ports.InputPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Component
public class AdminInput implements InputPort{
    @Autowired
    private PersonValidator personValidator;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private AdminServices adminService;

    @Autowired
    private PetValidator petValidator;

    private final String MENU = "Ingrese la opción: " + "\n 1. Para Crear Veterinario" + "\n 2. Para Crear Vendedor" + "\n 3. Para Crear un cliente" + "\n 4. Para Crear Una Mascota" 
    + "\n 5. ver facturas de un cliente" + "\n 6. ver ordenes de una mascota" + "\n 7. ver historias clínicas de una mascota" + "\n 8. Cerrar Sesion";
    @Override
    public void menu() throws Exception {
        boolean sesion = true;
        while (sesion) {
            sesion = options();
        }
    }
    private boolean options() {
		try {
			System.out.println(MENU);
			String option = Utils.getReader().nextLine();
			switch (option) {
			case "1": {
                this.creteVet();
			    return true;
			}
            case "2":
                this.creteSeller();
                return true;
            case "3":
                this.createCliend();
                return true;
            case "4":
                this.cretedPet();
			case "8" :{
				System.out.println("Se ha cerrado sesion");
				return false;
			}
			default:
				System.out.println("opcion no valida");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return true;
		}
	}
    
    private void creteVet() throws Exception{
        System.out.print("Ingrese el nombre del veterinario: ");
        String name = personValidator.nameValidator(Utils.getReader().nextLine());
        System.out.print("Ingrese el documento del veterinario: ");
        long document = personValidator.documentValudator(Utils.getReader().nextLine());
        System.out.print("Ingrese la edad del veterinario: ");
        int age = personValidator.ageValidator(Utils.getReader().nextLine());
        System.out.print("Ingrese el userName del veterinario: ");
        String userName = userValidator.userNameValidator(Utils.getReader().nextLine());
        System.out.print("Ingrese la contraseña de veterinario: ");
        String password = userValidator.passwordValidator(Utils.getReader().nextLine());
        User user = new User();
        user.setPersonName(name);
        user.setPersonDocument(document);
        user.setPersonAge(age);
        user.setUserName(userName);
        user.setPassword(password);
        user.setRole(1);
        adminService.registerVeterinarian(user);
    }

    private void creteSeller() throws Exception{
        System.out.print("Ingrese el nombre del vendedor: ");
        String name = personValidator.nameValidator(Utils.getReader().nextLine());
        System.out.print("Ingrese el documento del vendedor: ");
        long document = personValidator.documentValudator(Utils.getReader().nextLine());
        System.out.print("Ingrese la edad del vendedor: ");
        int age = personValidator.ageValidator(Utils.getReader().nextLine());
        System.out.print("Ingrese el userName del vendedor: ");
        String userName = userValidator.userNameValidator(Utils.getReader().nextLine());
        System.out.print("Ingrese la contraseña de vendedor: ");
        String password = userValidator.passwordValidator(Utils.getReader().nextLine());
        User user = new User();
        user.setPersonName(name);
        user.setPersonDocument(document);
        user.setPersonAge(age);
        user.setUserName(userName);
        user.setPassword(password);
        user.setRole(2);
        adminService.registerSeller(user);
    }

    private void createCliend() throws Exception{
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
        adminService.registerOwner(person);
    }
    private void cretedPet() throws Exception{
        System.out.print("Cedula del dueño: ");
        long personDocument = personValidator.documentValudator(Utils.getReader().nextLine());
        System.out.print("Nombre de la mascota: ");
        String name = petValidator.nameValidator(Utils.getReader().nextLine());
        System.out.print("Edad de la mascota: ");
        int age = petValidator.ageValidator(Utils.getReader().nextLine());
        System.out.print("Especie de la mascota: ");
        String species = petValidator.speciesValidator(Utils.getReader().nextLine());
        System.out.print("Raza de la mascota: ");
        String race = petValidator.raceValidator(Utils.getReader().nextLine());
        System.out.print("Peso de la mascota: ");
        double weigth = petValidator.weigthValidator(Utils.getReader().nextLine());
        Pet pet = new Pet();
        Person owner = new Person();
        owner.setPersonDocument(personDocument);
        pet.setPersonId(owner);
        pet.setPetName(name);
        pet.setPetAge(age);
        pet.setPetSpecies(species);
        pet.setPetRace(race);
        pet.setPetWeight(weigth);
        adminService.registerPet(pet);
    }
}
