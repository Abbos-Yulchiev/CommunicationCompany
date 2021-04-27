package uz.pdp.communicationcompany.payload;

import lombok.Data;
import uz.pdp.communicationcompany.entity.Services;

@Data
public class EntertainmentDTO {

    private String name;
    private String description;
    private Double price;
    private Services services;
}
