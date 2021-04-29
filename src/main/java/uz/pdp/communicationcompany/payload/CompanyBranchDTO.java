package uz.pdp.communicationcompany.payload;

import lombok.Data;
import uz.pdp.communicationcompany.entity.Address;
import uz.pdp.communicationcompany.entity.Company;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CompanyBranchDTO {

    @NotNull
    private Integer companyId;
    @NotNull
    private UUID addressId;

}
