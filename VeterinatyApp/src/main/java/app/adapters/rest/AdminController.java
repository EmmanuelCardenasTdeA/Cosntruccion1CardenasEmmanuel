package app.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.Exceptions.BusinessException;
import app.Exceptions.InputException;
import app.adapters.rest.request.UserRequest;
import app.domain.models.User;
import app.domain.services.AdminServices;
@RestController
public class AdminController {

    @Autowired
    private AdminServices adminServices;

    @PostMapping("/vet")
    public ResponseEntity registerVet(@RequestBody UserRequest request) {
        try {
            System.out.println(request.toString());
            User user = new User();
            user.setPersonDocument(request.getPersonDocument());
            user.setPersonName(request.getPersonName());
            user.setPersonAge(request.getPersonAge());
            user.setUserName(request.getUserName());
            user.setPassword(request.getPassword());
            user.setRole("veterinary");
            if(request.getPersonDocument() == 0 || request.getPersonName() == null || request.getPersonAge() ==0 || request.getUserName() == null || request.getPassword() == null) {
               throw new InputException("Datos Invalidos");
            }
            adminServices.registerVeterinarian(user);
            return new ResponseEntity("Veterinario Registrado", HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/Seller")
    public ResponseEntity registerSeller(@RequestBody UserRequest request) {
        try {
            System.out.println(request.toString());
            User user = new User();
            user.setPersonDocument(request.getPersonDocument());
            user.setPersonName(request.getPersonName());
            user.setPersonAge(request.getPersonAge());
            user.setUserName(request.getUserName());
            user.setPassword(request.getPassword());
            user.setRole("seller");
            if(request.getPersonDocument() == 0 || request.getPersonName() == null || request.getPersonAge() ==0 || request.getUserName() == null || request.getPassword() == null) {
               throw new InputException("Datos Invalidos");
            }
            adminServices.registerSeller(user);
            return new ResponseEntity("Vendedor Registrado", HttpStatus.OK);
        }catch(BusinessException be){
            return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(InputException ie){
            return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
