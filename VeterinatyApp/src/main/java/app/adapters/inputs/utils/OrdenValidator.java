package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class OrdenValidator extends SimpleValidator {

    public String nameValidator(String value) throws Exception{
        return stringValidator(value, "nombre del medicamento");
    }

    public double dosisValidator(String value) throws Exception{
        return doubleValidator(value, "dosis del medicamento");
    }

    public long ordenIdValidator(String value)throws Exception{
        return longValidator(value, "id orden");
    }
}
