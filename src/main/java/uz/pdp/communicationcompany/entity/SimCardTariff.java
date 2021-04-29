package uz.pdp.communicationcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimCardTariff {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private SimCard simCard;

    @ManyToOne
    private Tariffs tariff;

    private boolean status = true;         // true - active  false - not active

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp dateOfPurchase;      // Paket xarid qilingan sana va vaqt

    @Transient
    private Timestamp expireDate;

}