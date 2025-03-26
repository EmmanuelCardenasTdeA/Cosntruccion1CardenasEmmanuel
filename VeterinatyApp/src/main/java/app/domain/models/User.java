package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class User extends Person {
    private long userId;
    private String userName;
    private String password;
    private String role;

    public User(long userId, long personDocument, String personName, long personAge, String userName, String password, String role) {
        super(personDocument, personName, personAge);
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
