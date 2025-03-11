package app.adapters.pet.entity;

import app.adapters.persons.entity.PersonEntity;
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
@Table(name = "pet")
@Setter
@Getter
@NoArgsConstructor

public class PetEntity {
    @JoinColumn(name="document")
    @OneToOne
    private PersonEntity personEntity;
    @Column(name ="pet_name")
    private String petName;
    @Column(name="pet_age")
    private long petAge;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private long petId;
    @Column(name = "species")
    private String petSpecies;
    @Column(name = "pet_race")
    private String petRace;
    @Column(name = "pet_weight")
    private double petWeight;
}
