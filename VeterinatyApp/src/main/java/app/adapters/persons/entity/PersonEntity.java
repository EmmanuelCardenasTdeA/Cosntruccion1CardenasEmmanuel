package app.adapters.persons.entity;

import app.domain.models.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@Setter
@Getter
@NoArgsConstructor

public class PersonEntity {
    public PersonEntity(Person person){
        this.personDocument=person.getPersonDocument();
        this.personName=person.getPersonName();
        this.personAge=person.getPersonAge();
    }

    @Id
    @Column(name = "document")
    private long personDocument;
    @Column(name = "name")
    private String personName;
    @Column(name = "age")
    private long personAge;
}
