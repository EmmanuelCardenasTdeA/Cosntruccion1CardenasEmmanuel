package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class User extends Person {

    private String userName;
    private String password;
    private int role;


    public User(long personDocument, String personName, long personAge, String userName, String password, int role) {
        super(personDocument, personName, personAge);
        this.userName = userName;
        this.password = password;
        this.role = role;

    }    
}
