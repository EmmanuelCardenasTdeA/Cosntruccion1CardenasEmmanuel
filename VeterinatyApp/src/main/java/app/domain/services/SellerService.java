package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.models.Invoiced;
import app.domain.models.Orden;
import app.ports.InvoicedPort;
import app.ports.OrdenPort;
@Service
public class SellerService {
    @Autowired
    private OrdenPort ordenPort;
    @Autowired
    private InvoicedPort invoicedPort;

    public void saveInvoiced(Invoiced invoiced) throws Exception {
        invoicedPort.saveInvoiced(invoiced);
    }

    public Orden getOrdenByOrdenId(long ordenId)throws Exception {
        Orden orden = ordenPort.findByOrdenId(ordenId);
        if(orden == null){
            throw new Exception("No existe una orden con ese ID");
        }
        System.out.println(orden.getOrdenStatus());
        return orden;
    }

    public Invoiced getInvoicedByInvoicedId(long invoicedId) throws Exception {
        Invoiced invoiced = invoicedPort.findByInvoicedId(invoicedId);
        if(invoiced == null){
            throw new Exception("No existe una factura con ese ID");
        }
        System.out.println(invoiced.getOrden().getOrdenStatus());
        return invoiced;
    }

    public List<Invoiced> getAllInvoiced() throws Exception{
       return invoicedPort.getAllInvoiced();
    }

    public List<Orden> getAllOrden() throws Exception {
        return ordenPort.getAllOrden();
    }

}
