package app.adapters.clinicaRecord.entity;

import java.sql.Timestamp;

import app.adapters.orden.entity.OrdenEntity;
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
@Table(name="clinica")
@Setter
@Getter
@NoArgsConstructor
public class ClinicaRecordEntity {
    @Id
    @Column(name="clinica_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clinicaId;
    @Column(name="date")
    private Timestamp date;
    @Column(name ="consultation")
    private String consultation;
    @Column(name="syntomatology")
    private String syntomatology;
    @Column(name="diagnostic")
    private String diagnostic;
    @Column(name="treatment")
    private String treatment;
    //Obtener Cliente, Mascota y Veterinario
    @JoinColumn(name="orden_id")
    @OneToOne
    private OrdenEntity orden;
    @Column(name="vacum_history")
    private String vacumHistory;
    @Column(name="allergy_medicines")
    private String allergyMedicines;
    @Column(name="details_treatement")
    private String detailsTreatement;

}
