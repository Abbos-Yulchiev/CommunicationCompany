package uz.pdp.communicationcompany.payload;

import lombok.Data;
import java.sql.Date;

@Data
public class SimCardServiceDTO {

    private Integer simCardId;
    private Integer servicesId;
    private Boolean status;
    private Date startDate;
    private Date endDate;
}
