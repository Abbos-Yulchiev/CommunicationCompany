package uz.pdp.communicationcompany.payload;

import lombok.Data;

@Data
public class UssdCodeDTO {

    private String ussdCode;
    private String description;
    private Integer servicesId;
}
