package uz.pdp.communicationcompany.payload;

import lombok.Data;
import uz.pdp.communicationcompany.entity.Address;
import uz.pdp.communicationcompany.entity.SimCard;

import java.util.Set;
import java.util.UUID;

@Data
public class UserDTO {

    private String firstName;

    private String lastName;

    private String passportId;

    private Address address;

    private Set<SimCard> simCards;
}
