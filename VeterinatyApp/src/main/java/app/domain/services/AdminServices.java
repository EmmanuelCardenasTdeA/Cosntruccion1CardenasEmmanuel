package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.Exceptions.BusinessException;
import app.domain.models.User;
import app.ports.PersonPort;
import app.ports.UserPort;

@Service
public class AdminServices{

    @Autowired
    private PersonPort personPort;
    @Autowired
    private UserPort userPort;
 

    public void registerVeterinarian(User user)throws Exception{
        if(personPort.existsPerson(user.getPersonDocument())){
            throw new BusinessException("Ya existe un usuario con ese documento");
        }
        if(userPort.existUserName(user.getUserName())){
            throw new BusinessException("Ya existe un usuario con ese nombre");
        }
        personPort.savePerson(user);
        userPort.saveUser(user);
    }

    public void registerSeller(User user)throws Exception{
        if(personPort.existsPerson(user.getPersonDocument())){
            throw new BusinessException("Ya existe un usuario con ese documento");
        }
        if(userPort.existUserName(user.getUserName())){
            throw new BusinessException("Ya existe un usuario con ese nombre de usuario");
        }
        personPort.savePerson(user);
        userPort.saveUser(user);
        
    }
    
}
