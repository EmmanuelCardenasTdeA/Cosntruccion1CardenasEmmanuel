package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class ClinicaValidator extends SimpleValidator{
    public String stringClinicaValidator(String value)throws Exception{
        return stringValidator(value, "datos de hitoria clinica");
    }
    public long clinicaIdValidator(String value)throws Exception{
        return longValidator(value, "id clinica");
    }
}
