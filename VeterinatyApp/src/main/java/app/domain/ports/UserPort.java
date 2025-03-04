package app.domain.ports;

import app.domain.models.Person;
import app.domain.models.User;

public interface UserPort {

    public boolean existUserName(String userName);

    public void saveUser(User user);

    public User findByPersonDocument(Person person);

}
