package uz.pdp.communicationcompany.payload;

import lombok.Data;
import uz.pdp.communicationcompany.entity.Details;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
public class TariffsDTO {

    @NotNull
    private String name;

    @NotNull
    private boolean physicalPerson;

    @NotNull
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private List<Details> details;
}
