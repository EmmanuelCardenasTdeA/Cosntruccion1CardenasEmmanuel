package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class PetValidator extends SimpleValidator{
    public String nameValidator(String value) throws Exception{
        return stringValidator(value, "nombre de la mascota");
    }

    public int ageValidator(String value) throws Exception{
        return intValidator(value, "eda de la persona");
    }

    public long idValidator(String value) throws Exception{
        return longValidator(value, "id de la mascota");
    }

    public String speciesValidator(String value) throws Exception{
        return stringValidator(value, "especie de la mascota");
    }

    public String raceValidator(String value) throws Exception{
        return stringValidator(value, "raza de la mascota");
    }

    public double weigthValidator(String value) throws Exception{
        return doubleValidator(value, "peso");
    }
}
