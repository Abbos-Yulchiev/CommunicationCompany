package uz.pdp.communicationcompany.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class TourniquetHistoryDTO {

    Integer tourniquetId;
    boolean goingIn;
    UUID employeeId;
}
