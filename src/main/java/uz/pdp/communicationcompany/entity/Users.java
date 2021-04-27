package uz.pdp.communicationcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;

    private String lastName;

    private String passportId;

    @ManyToOne
    private Address address;
}
