package app.adapters.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.users.entity.UserEntity;
import app.adapters.users.repository.UserRepository;
import app.domain.models.User;
import app.ports.UserPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
public class UserAdapter implements UserPort{
    @Autowired
    private UserRepository userRepository;

    private User user;

    @Override
    public User findByUserName(User user) throws Exception{
        UserEntity userEntity = userRepository.findByUserName(user.getUserName());
        if(userEntity == null){
            throw new Exception("No existe un usuario con ese nombre");
        }
        return userAdapter(userEntity);
    }


    @Override
    public boolean existUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    @Override
    public void saveUser(User user){
        System.out.println("datos que llegan al user adapter: " + user.getPersonName() + " " + user.getPersonDocument());
        UserEntity userEntity = userAdapter(user);
        System.out.println("datos antes de ser guardados: "+ userEntity.getUserName() + " " + userEntity.getPerson().getDocument());
        userRepository.save(userEntity);
        user.setUserId(userEntity.getUserId());
    }

    @Override
    public User findByPersonDocument(Long personDocument)throws Exception {
        UserEntity userEntity = userRepository.findByPersonDocument(personDocument);
        if(userEntity==null){throw new Exception("No existe un usuario con ese documento");}
        return userAdapter(userEntity);
    }
    

    private User userAdapter(UserEntity userEntity){
        User user = new User();
        user.setPersonDocument(userEntity.getPerson().getDocument());
        user.setPersonName(userEntity.getPerson().getName());
        user.setPersonAge(userEntity.getPerson().getAge());
        user.setUserId(userEntity.getUserId());
        user.setUserName(userEntity.getUserName());
        user.setPassword(userEntity.getPassword());
        user.setRole(userEntity.getRole());
        return user;
    }

    private UserEntity userAdapter(User user){
        //reemplazo metodo personAdpter por mala implementación
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(user.getPersonName());
        personEntity.setDocument(user.getPersonDocument());
        personEntity.setAge(user.getPersonAge());

        UserEntity userEntity = new UserEntity();
        userEntity.setPerson(personEntity);
        userEntity.setUserId(user.getUserId());
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        return userEntity;
    }


}
