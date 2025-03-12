package app.adapters.users;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.users.entity.UserEntity;
import app.adapters.users.repository.UserRepository;
import app.domain.models.Person;
import app.domain.models.User;
import app.ports.UserPort;

public class UserAdapter implements UserPort{
    private UserRepository userRepository;
    private User user;

    @Override
    public boolean existUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    @Override
    public void saveUser(User user){
        UserEntity userEntity = userEntityAdapter(user);
        userRepository.save(userEntity);
        user.setUserId(userEntity.getUserId());
    }

    @Override
    public User findByPersonDocument(Long personDocument) {
        UserEntity userEntity = userRepository.findByPersonDocument(personDocument);
        return userAdapter(userEntity);
    }
    

    private User userAdapter(UserEntity userEntity){
        if(userEntity == null){
            return null;
        }

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

    private UserEntity userEntityAdapter(User user){
        
        PersonEntity personEntity = personEntityAdapter(user);
        UserEntity userEntity = new UserEntity();
        userEntity.setPerson(personEntity);
        userEntity.setUserId(user.getUserId());
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        return userEntity;
    }

    private PersonEntity personEntityAdapter(Person person){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setDocument(user.getPersonDocument());
        personEntity.setName(user.getPersonName());
        personEntity.setAge(user.getPersonAge());
        return personEntity;
    }



}
