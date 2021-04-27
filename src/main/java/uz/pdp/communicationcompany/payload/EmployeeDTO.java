package uz.pdp.communicationcompany.payload;

import lombok.Data;
import uz.pdp.communicationcompany.entity.Address;

import java.util.Set;

@Data
public class EmployeeDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Integer> rolesListId;
    private Set<Integer> companyBranchListId;
    private Address address;
}
