package uz.pdp.communicationcompany.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class SimCardDTO {

    private Integer code;           // simkarta kodi : 94, 93, 97, ...
    private UUID customerId;
    private Integer companyId;
}
