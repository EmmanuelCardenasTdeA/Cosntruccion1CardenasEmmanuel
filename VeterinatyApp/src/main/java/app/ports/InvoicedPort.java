package app.ports;

import java.util.List;

import app.domain.models.Invoiced;

public interface InvoicedPort {
    public void saveInvoiced(Invoiced invoiced)throws Exception;

    public Invoiced findByInvoicedId(long invoicedId)throws Exception;

    public List<Invoiced> getAllInvoiced()throws Exception;
}
