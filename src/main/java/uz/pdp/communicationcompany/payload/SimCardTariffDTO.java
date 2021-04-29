package uz.pdp.communicationcompany.payload;

import lombok.Data;

@Data
public class SimCardTariffDTO {
    private Integer simCardId;
    private Integer tariffId;
    private boolean status;
}
