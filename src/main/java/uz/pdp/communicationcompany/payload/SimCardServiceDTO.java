package uz.pdp.communicationcompany.payload;


import lombok.Data;
import uz.pdp.communicationcompany.entity.Services;

import java.sql.Date;

@Data
public class SimCardServiceDTO {

    private Integer simCardId;
    private Services services;
    private boolean status;
    private Date startDate;
    private Date endDate;
}
