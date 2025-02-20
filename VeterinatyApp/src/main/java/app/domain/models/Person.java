package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class Person {
    private long document;
    private String name;
    private long age;
    private int role;

    public Person(long document, String name, long age, int role) {
        this.document = document;
        this.name = name;
        this.age = age;
        this.role = role;
    }

}
