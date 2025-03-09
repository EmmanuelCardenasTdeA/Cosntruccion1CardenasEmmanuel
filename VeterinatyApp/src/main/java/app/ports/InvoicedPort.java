package app.ports;

import java.util.List;

import app.domain.models.Invoiced;

public interface InvoicedPort {
    public List<Invoiced> getAllInvoices();

}
