package app.adapters.users.entity;

import app.adapters.persons.entity.PersonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor

public class UserEntity {

    @Id
    @Column(name ="user_id")
    private long userId;
    @JoinColumn(name = "person_document")
    @OneToOne
    private PersonEntity person;
    @Column(name="user_name")
    private String userName;
    @Column(name="password")
    private String password;
    @Column(name = "role")
    private int role;
    @Column(name ="role_name")
    private String roleName;
    
}
