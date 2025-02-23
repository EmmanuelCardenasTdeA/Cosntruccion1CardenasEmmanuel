package app.adapters.users;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.users.entity.UserEntity;
import app.adapters.users.repository.UserRepository;
import app.domain.models.Person;
import app.domain.models.User;
import app.domain.ports.UserPort;

public class UserAdapter implements UserPort{
    private UserRepository userRepository;
    private User user;

    public boolean existUserName(String userName){
        return userRepository.existsUserName(userName);
    }

    public void saveUser(User user){
        UserEntity userEntity = userEntityAdapter(user);
        userRepository.save(userEntity);
        user.setPersonDocument(userEntity.getPersonDocument());
    }

    public User findByDocument(Person person){
        PersonEntity personEntity = personEntityAdapter(person);
        UserEntity userEntity = userRepository.findByPersonDocument(personEntity);
        User user = userAdapter(userEntity);
        return user;
    }

    private User userAdapter(UserEntity userEntity){
        if(userEntity == null){
            return null;
        }

        User user = new User();
        user.setPersonDocument(userEntity.getPerson().getPersonDocument());
        user.setPersonName(userEntity.getPerson().getPersonName());
        user.setPersonAge(userEntity.getPerson().getPersonAge());
        user.setUserName(userEntity.getUserName());
        user.setPassword(userEntity.getPassword());
        user.setRole(userEntity.getRole());
        return user;
    }

    private UserEntity userEntityAdapter(User user){
        PersonEntity personEntity = personEntityAdapter(user);

        UserEntity userEntity = new UserEntity();
        userEntity.setPerson(personEntity);
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        return userEntity;
    }

    private PersonEntity personEntityAdapter(Person person){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setPersonDocument(user.getPersonDocument());
        personEntity.setPersonName(user.getPersonName());
        personEntity.setPersonAge(user.getPersonAge());
        return personEntity;
    }
}
