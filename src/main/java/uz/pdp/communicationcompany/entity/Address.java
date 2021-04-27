package uz.pdp.communicationcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue()
    private UUID id;

    @Column(nullable = false)
    private String cityName;

    @Column(nullable = false)
    private String streetName;
}
