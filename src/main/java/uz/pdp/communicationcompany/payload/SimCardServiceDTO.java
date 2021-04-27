package uz.pdp.communicationcompany.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class SimCardServiceDTO {

    private String phoneNumber;

    private boolean sell;

    private Integer companyId;

    private UUID usersId;

    private Integer tariffsId;
}
