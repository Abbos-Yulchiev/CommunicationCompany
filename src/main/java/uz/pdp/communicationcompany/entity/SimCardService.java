package uz.pdp.communicationcompany.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SimCardService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private SimCard simCard;

    @ManyToOne
    private Services services;

    private boolean status;

    private Date startDate;

    private Date endDate;
}
