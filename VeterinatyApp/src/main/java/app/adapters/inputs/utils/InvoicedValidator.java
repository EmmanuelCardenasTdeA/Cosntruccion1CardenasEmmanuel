package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class InvoicedValidator extends SimpleValidator {
    public double amoutValidator(String value)throws Exception{
        return doubleValidator(value, "Valor de la factura");
    }

    public long quantityValidator(String value) throws Exception{
        return longValidator(value, "Cantidad de productos");
    }

    public long invoicedIdValidator(String value)throws Exception{
        return longValidator(value, "id de la factura");
    }
}
