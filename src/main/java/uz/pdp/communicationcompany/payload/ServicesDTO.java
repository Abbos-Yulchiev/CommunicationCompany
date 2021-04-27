package uz.pdp.communicationcompany.payload;


import lombok.Data;
import uz.pdp.communicationcompany.entity.USSDCodes;

import java.util.Set;

@Data

public class ServicesDTO {

    private String description;

    private Double price;

    private String name;

    private Set<USSDCodes> ussdCodes;
}
