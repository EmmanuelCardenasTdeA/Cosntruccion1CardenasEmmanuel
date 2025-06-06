package app.adapters.invoiced.entity;

import java.sql.Timestamp;
import app.adapters.orden.entity.OrdenEntity;

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
@Table(name = "invoiced")
@Setter
@Getter
@NoArgsConstructor

public class InvoicedEntity{
    @Id
    @Column(name = "invoiced_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoicedId;
    @JoinColumn(name="orden_id")
    @OneToOne
    private OrdenEntity orden;
    @Column(name="amount")
    private double amount;
    @Column(name="medication_quantity")
    private long medicationQuantity;
    @Column(name ="product")
    private String product;
    @Column(name="date")
    private Timestamp date;
}
