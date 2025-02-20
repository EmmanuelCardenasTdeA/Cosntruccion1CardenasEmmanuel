package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class Person {

    private long personDocument;
    private String personName;
    private long personAge;

    public Person(long personDocument, String personName, long personAge) {
        this.personDocument = personDocument;
        this.personName = personName;
        this.personAge = personAge;
    }
}
