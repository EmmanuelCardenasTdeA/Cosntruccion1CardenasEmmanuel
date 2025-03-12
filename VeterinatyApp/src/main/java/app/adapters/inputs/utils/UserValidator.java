package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class UserValidator extends SimpleValidator{

    public String nameValidator(String value) throws Exception{
        return stringValidator(value, "nombre de la persona");
    }

    public long documentValudator(String value) throws Exception{
        return longValidator(value, "numero de documento");
    }
    
    public int ageValidator(String value) throws Exception{
        return intValidator(value, "eda de la persona");
    }

    public String userNameValidator(String value) throws Exception{
        return stringValidator(value, " nombre de usuario ");
    }

    public String passwordValidator(String value) throws Exception{
        return stringValidator(value, " contraseña de usuario ");
    }
    
}
