package app.adapters.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.Exceptions.BusinessException;
import app.Exceptions.InputException;
import app.adapters.rest.request.InvoicedRequest;
import app.adapters.rest.resposive.InvoicedRest;
import app.adapters.rest.resposive.OrderRest;
import app.adapters.rest.resposive.PersonRest;
import app.adapters.rest.resposive.PetRest;
import app.domain.models.Invoiced;
import app.domain.models.Orden;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.domain.services.SellerService;
import app.ports.OrdenPort;

@RestController
public class SellerController {

    @Autowired
    private OrdenPort ordenPort;

    @Autowired
    private SellerService sellerService;
    
    @PostMapping("/invoiced")
    public ResponseEntity saveInvoiced(@RequestBody InvoicedRequest request){
        try {
            System.out.println(request.toString());
            long ordenId = request.getOrderId();
            System.out.println(ordenId);
            Orden orden = ordenPort.findByOrdenId(ordenId);
            if(orden == null){
                throw new BusinessException("No existe una orden con ese ID");
            }
            Invoiced invoiced = new Invoiced();
            invoiced.setOrden(orden);
            invoiced.setAmount(request.getAmount());
            invoiced.setMedicationQuantity(request.getMedicationQuantity());

            if(request.getProduct() != null){
                invoiced.setProduct(request.getProduct());
            }else{
                invoiced.setProduct("Sin productos adicionales");
            } 
            if(request.getAmount() ==0 || request.getMedicationQuantity() ==0){
                throw new InputException("Datos Invalidos");
            }

            sellerService.saveInvoiced(invoiced);
            return new ResponseEntity("Factura Creada", HttpStatus.OK);
        }catch (BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/invoiced/{invoicedId}")
    public ResponseEntity getInvoiced(@PathVariable long invoicedId){
        try {
            Invoiced invoiced = sellerService.getInvoicedByInvoicedId(invoicedId);
            InvoicedRest invoicedRest = invoicedRestAdapter(invoiced);
            return new ResponseEntity(invoicedRest,HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/GetAllInvoiced")
    public ResponseEntity getInvoiced(){
        try {
            List<Invoiced> invoiced = sellerService.getAllInvoiced();
            List<InvoicedRest> invoicedRest = invoicedAdapterList(invoiced);
            return new ResponseEntity(invoicedRest,HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private InvoicedRest invoicedRestAdapter(Invoiced invoiced){
        InvoicedRest invoicedRest = new InvoicedRest();
        invoicedRest.setInvoicedId(invoiced.getInvoicedId());
        invoicedRest.setOrden(orderRestAdapter(invoiced.getOrden()));
        invoicedRest.setAmount(invoiced.getAmount());
        invoicedRest.setMedicationQuantity(invoiced.getMedicationQuantity());
        invoicedRest.setProduct(invoiced.getProduct());
        invoicedRest.setDate(invoiced.getDate());
        return invoicedRest;
    }

    private PetRest petRestAdapter(Pet pet){
        PetRest petRest = new PetRest();
        petRest.setPetId(pet.getPetId());
        petRest.setPetName(pet.getPetName());
        petRest.setPetAge(pet.getPetAge());
        petRest.setPetSpecies(pet.getPetSpecies());
        petRest.setPetRace(pet.getPetRace());
        petRest.setPetWeight(pet.getPetWeight());
        return petRest;
    }

    private PersonRest personRestAdapter(Person person){
        PersonRest personRest = new PersonRest();
        personRest.setPersonDocument(person.getPersonDocument());
        personRest.setPersonName(person.getPersonName());
        personRest.setPersonAge(person.getPersonAge());
        return personRest;
    }

    private PersonRest userRestAdapter(User user){
        PersonRest personRest = new PersonRest();
        personRest.setPersonDocument(user.getPersonDocument());
        personRest.setPersonName(user.getPersonName());
        personRest.setPersonAge(user.getPersonAge());
        return personRest;
    }

    private OrderRest orderRestAdapter(Orden orden){
        OrderRest orderRest = new OrderRest();
        orderRest.setOrdenId(orden.getOrdenId());
        orderRest.setPet(petRestAdapter(orden.getPet()));
        orderRest.setOwner(personRestAdapter(orden.getOwner()));
        orderRest.setVeterinarian(userRestAdapter(orden.getVeterinarian()));
        orderRest.setMedicationName(orden.getMedicationName());
        orderRest.setMedicationDosis(orden.getMedicationDosis());
        orderRest.setOrdenStatus(orden.getOrdenStatus());
        orderRest.setDate(orden.getDate());
        return orderRest;
    }

    public List<InvoicedRest> invoicedAdapterList(List<Invoiced> invoicedList)throws Exception{
        List<InvoicedRest> invoicedRestList = new ArrayList<>();
        for(Invoiced invoiced : invoicedList){
            invoicedRestList.add(invoicedRestAdapter(invoiced));
        }
        return invoicedRestList;
    }
    
}
