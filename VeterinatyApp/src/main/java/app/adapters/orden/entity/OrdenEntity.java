package app.adapters.orden.entity;

import java.sql.Date;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.adapters.users.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orden")
@Setter
@Getter
@NoArgsConstructor
public class OrdenEntity {
    @Id
    @Column(name = "orden_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ordenId;
    @JoinColumn(name="pet_id")
    @OneToOne
    private PetEntity petEntity;
    @JoinColumn(name =("document"))
    @OneToOne
    private PersonEntity personEntity;
    @JoinColumn(name = "user_id")
    @OneToOne
    private UserEntity userEntity;
    @Column(name="medication_name")
    private String medicationName;
    @Column(name="medication_dosis")
    private double medicationDosis;
    @Column(name = "date")
    private Date date;
}
