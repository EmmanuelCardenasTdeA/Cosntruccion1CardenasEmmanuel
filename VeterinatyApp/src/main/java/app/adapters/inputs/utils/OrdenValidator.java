package app.adapters.inputs.utils;

public class OrdenValidator extends SimpleValidator {

    public String nameValidator(String value) throws Exception{
        return stringValidator(value, "nombre del medicamento");
    }

    public long dosisValidator(String value) throws Exception{
        return longValidator(value, "dosis del medicamento");
    }
}
