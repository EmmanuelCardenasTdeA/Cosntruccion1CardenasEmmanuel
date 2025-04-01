package app.adapters.inputs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.inputs.utils.UserValidator;
import app.adapters.inputs.utils.Utils;
import app.domain.models.User;
import app.domain.services.LoginService;
import app.ports.InputPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@Component
public class LoginInput implements InputPort{

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private AdminInput adminInput;

    @Autowired
    private VeterinarianInput veterinarianInput;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SellerInput sellerInput;

    private final String MENU = "Ingrese la opcion que desea:\n 1. iniciar sesion \n 2. salir";

    private Map<String, InputPort> inputs = new HashMap<>();

    public LoginInput(AdminInput adminInput, VeterinarianInput veterinarianInput,
        SellerInput sellerInput) {
        super();
        this.adminInput = adminInput;
        this.veterinarianInput = veterinarianInput;
        this.sellerInput = sellerInput;
        this.inputs = new HashMap<>();
    }



    @Override
    public void menu() throws Exception {
       boolean sesion = true;
       while (sesion) {sesion = options();}
    }

    private boolean options()throws Exception{
        try {
            System.out.println(MENU);
            String option = Utils.getReader().nextLine();
            switch (option) {
                case "1":
                    this.login();
                    return true;
                case "2":
                System.out.println("Saliendo...");
                return false;
                default:
                    System.out.println("Opción Invalida");
                    return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private void login(){
        try {
            
            inputs.put("admin", adminInput);
            inputs.put("veterinary", veterinarianInput);
            inputs.put("seller", sellerInput);

            System.out.print("Ingrese Usuario: ");
            String userName = userValidator.userNameValidator(Utils.getReader().nextLine());
            System.out.print("Ingrese Contraseña: ");
            String password = userValidator.passwordValidator(Utils.getReader().nextLine());
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            user = loginService.login(user);
            InputPort inputPort = inputs.get(user.getRole());
            inputPort.menu();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
