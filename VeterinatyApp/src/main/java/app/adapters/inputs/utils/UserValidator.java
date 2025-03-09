package app.adapters.inputs.utils;

public class UserValidator extends SimpleValidator{

    public String userNameValidator(String value) throws Exception{
        return stringValidator(value, " nombre de usuario ");
    }

    public String passwordValidator(String value) throws Exception{
        return stringValidator(value, " contraseña de usuario ");
    }
    
}
