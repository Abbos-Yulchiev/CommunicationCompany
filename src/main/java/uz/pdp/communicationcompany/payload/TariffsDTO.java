package uz.pdp.communicationcompany.payload;

import lombok.Data;
import uz.pdp.communicationcompany.entity.Detail;

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
    private List<Detail> detail;
}
